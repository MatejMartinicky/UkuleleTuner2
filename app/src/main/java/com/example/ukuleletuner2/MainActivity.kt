package com.example.ukuleletuner2

import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.example.ukuleletuner2.fastFurierTransform.FourierTransform
import com.example.ukuleletuner2.recorder.AndroidAudioRecorder
import com.example.ukuleletuner2.recorder.AudioRecorder


class MainActivity : ComponentActivity() {
    private var isRecording by mutableStateOf(false)
    private val sampleRate = 44100
    private val bufferSize = 2048
    private val audioRecorder: AudioRecorder = AndroidAudioRecorder()
    private val fourierTransform = FourierTransform(sampleRate, bufferSize)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request microphone permission
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )

        setContent {
            Navigation()

            //TunerScreen()
        }
    }
}