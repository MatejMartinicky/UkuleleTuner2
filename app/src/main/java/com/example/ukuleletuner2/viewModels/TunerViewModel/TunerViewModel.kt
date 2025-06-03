package com.example.ukuleletuner2.viewModels.TunerViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ukuleletuner2.recorder.TuningRecorder
import com.example.ukuleletuner2.tuneDetector.TuningDetector
import com.example.ukuleletuner2.widgets.UkuleleString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TunerViewModel(context: Context) : ViewModel() {
    private val recorder = TuningRecorder(context)
    private val detector = TuningDetector()

    val isRecording = MutableStateFlow(false)
    val frequency = MutableStateFlow(0.0)
    val status = MutableStateFlow("")

    fun toggle() {
        if (isRecording.value) {
            isRecording.value = false
        } else {
            start()
        }
    }

    private fun start() {
        isRecording.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val buffer = ShortArray(2048)
                try {
                    recorder.start()
                    while (isRecording.value) {
                        if (recorder.read(buffer) > 0) {
                            frequency.value = detector.getDetectedFrequency(buffer)
                            status.value = getStatus(detector.getStringTuningStatus(buffer))
                        }
                        delay(100)
                    }
                } catch (e: Exception) {
                    status.value = "Error"
                } finally {
                    recorder.stop()
                }
            }
        }
    }

    private fun getStatus(strings: Map<UkuleleString, Boolean>): String {
        val inTune = strings.entries.find { it.value }?.key
        return inTune?.name ?: "Tune"
    }
}