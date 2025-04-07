package com.example.ukuleletuner2

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit
) {
    var isRecording =false
    val sampleRate = 44100
    val bufferSize = 2048
    val audioRecorder: AudioRecorder = AndroidAudioRecorder()
    val fourierTransform = FourierTransform(sampleRate, bufferSize)

    var detectedFrequency by remember { mutableStateOf(0.0) }
    var tuningStatus by remember { mutableStateOf("Waiting...") }

    LaunchedEffect(isRecording) {
        if (isRecording) {
            withContext(Dispatchers.IO) {
                val buffer = ShortArray(bufferSize)
                audioRecorder.start()
                while (isRecording) {
                    val read = audioRecorder.read(buffer)
                    if (read > 0) {
                        detectedFrequency = fourierTransform.processFFT(buffer)
                        tuningStatus = evaluateTuning(detectedFrequency)
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
                    Text("") //fix
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF66BB6A)
                ),
                navigationIcon = {
                    IconButton(onClick = { /* todo */ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
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
                            text = "Chords",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable(onClick = { /* todo */ })
                        )

                        IconButton(onClick = { onNavigateToSettings() }) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint = Color.White
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
                    .background(Color.Transparent)
                    .padding(paddingValues)
            ) {
                val (instrumentLayout, frequency, currentTuning, startButton, settingButton) = createRefs()

                Text(
                    "Detected Frequency: ${"%.2f".format(detectedFrequency)} Hz",
                    color = Color.Gray,
                    modifier = Modifier.constrainAs(frequency) {
                        top.linkTo(settingButton.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                Text(
                    "Tuning: $tuningStatus",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.constrainAs(currentTuning) {
                        top.linkTo(frequency.bottom, margin = 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                Button(
                    onClick = { onNavigateToChords() }, //todo just to screenshot change to start tooning
                    modifier = Modifier.constrainAs(startButton) {
                        top.linkTo(currentTuning.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    Text(if (isRecording) "Stop" else "Start Tuning")
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

private fun evaluateTuning(frequency: Double): String {
    val ukuleleNotes = mapOf(
        "G4" to 392.00, "C4" to 261.63,
        "E4" to 329.63, "A4" to 440.00
    )
    val closestNote = ukuleleNotes.minByOrNull { (_, noteFreq) ->
        abs(noteFreq - frequency)
    }
    if (closestNote == null) return "Unknown"
    val difference = frequency - closestNote.value

    return when {
        difference < -10 -> "Too Low (${closestNote.key})"
        difference > 10 -> "Too High (${closestNote.key})"
        else -> "In Tune (${closestNote.key})"
    }
}

