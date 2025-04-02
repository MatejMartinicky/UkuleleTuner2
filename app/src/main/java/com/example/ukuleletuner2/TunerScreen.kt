package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.ukuleletuner2.fastFurierTransform.FourierTransform
import com.example.ukuleletuner2.recorder.AndroidAudioRecorder
import com.example.ukuleletuner2.recorder.AudioRecorder
import kotlinx.coroutines.Dispatchers

@Composable
fun TunerScreen(onNavigateToSettings: () -> Unit) {
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) //android guidelies standard padding
            .padding(top = 24.dp) //they had some green thing that seemed bigger
            .background(Color.Transparent)
    ) {
        val (instrumentLayout, frequency, currentTuning, startButton, settingButton) = createRefs()

        Button(
            onClick = onNavigateToSettings,
            modifier = Modifier.constrainAs(settingButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            ) {
                Text(
                    text = "Settings"
                    )
            }

        Text(
            "Detected Frequency: ${"%.2f".format(detectedFrequency)} Hz",
            color = Color.Gray,
            modifier = Modifier.constrainAs(frequency) {
                top.linkTo(settingButton.bottom) //change when adding setting TODO
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
            onClick = { isRecording = !isRecording },
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

