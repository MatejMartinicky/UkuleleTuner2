package com.example.ukuleletuner2

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ukuleletuner2.customLayouts.InstrumentLayout
import kotlinx.coroutines.withContext
import kotlin.math.abs
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.ukuleletuner2.fastFurierTransform.FourierTransform
import com.example.ukuleletuner2.recorder.AndroidAudioRecorder
import com.example.ukuleletuner2.recorder.AudioRecorder
import kotlinx.coroutines.Dispatchers
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit
) {
    val context = LocalContext.current

    var isRecording =false
    val sampleRate = 44100
    val bufferSize = 2048
    val audioRecorder: AudioRecorder = AndroidAudioRecorder(context)
    val fourierTransform = FourierTransform(sampleRate, bufferSize)

    var detectedFrequency by remember { mutableStateOf(0.0) }
    var tuningStatus by remember { mutableStateOf(context.getString(R.string.tuning_waiting)) }

    LaunchedEffect(isRecording) {
        if (isRecording) {
            withContext(Dispatchers.IO) {
                val buffer = ShortArray(bufferSize)
                //remove this dummy file when implementation changes
                val dummyFile = File(context.filesDir, "dummy_file.mp3")
                audioRecorder.start(dummyFile) //changed for now code to  compile change back
                while (isRecording) {
                    val read = audioRecorder.read(buffer)
                    if (read > 0) {
                        detectedFrequency = fourierTransform.processFFT(buffer)
                        tuningStatus = evaluateTuning(detectedFrequency, context)
                    }
                }
                audioRecorder.stop()
            }
        }
    }

    //todo bad redo into paths
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.tuner_screen_title))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = { /* todo */ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = stringResource(R.string.menu_content_description)
                        )
                    }
                },

                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = stringResource(R.string.chords_navigation_text),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable(onClick = { onNavigateToChords } )
                        )

                        IconButton(onClick = { onNavigateToSettings() }) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = stringResource(R.string.settings_content_description),
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
                    .padding(paddingValues)
            ) {
                val (instrumentLayout, frequency, currentTuning, startButton, settingButton) = createRefs()

                Text(
                    stringResource(R.string.detected_frequency, "%.2f".format(detectedFrequency)),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.constrainAs(frequency) {
                        top.linkTo(settingButton.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                Text(
                    stringResource(R.string.tuning_status, tuningStatus),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.constrainAs(currentTuning) {
                        top.linkTo(frequency.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                Button(
                    onClick = { onNavigateToChords() }, //todo just to screenshot change to start tooning
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.constrainAs(startButton) {
                        top.linkTo(currentTuning.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    Text(
                        if (isRecording)
                            stringResource(R.string.stop_tuning)
                        else
                            stringResource(R.string.start_tuning)
                    )
                }

                InstrumentLayout(
                    modifier = Modifier.constrainAs(instrumentLayout) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    )
}

private fun evaluateTuning(frequency: Double,context: Context): String {
    val ukuleleNotes = mapOf(
        context.getString(R.string.g4) to 392.00,
        context.getString(R.string.c4) to 261.63,
        context.getString(R.string.e4) to 329.63,
        context.getString(R.string.a4) to 440.00
    )
    val closestNote = ukuleleNotes.minByOrNull { (_, noteFreq) ->
        abs(noteFreq - frequency)
    }
    if (closestNote == null) return  context.getString(R.string.tuning_unknown)
    val difference = frequency - closestNote.value

    return when {
        difference < -10 -> context.getString(R.string.tuning_too_low, closestNote.key)
        difference > 10 -> context.getString(R.string.tuning_too_high, closestNote.key)
        else -> context.getString(R.string.tuning_in_tune, closestNote.key)
    }
}

