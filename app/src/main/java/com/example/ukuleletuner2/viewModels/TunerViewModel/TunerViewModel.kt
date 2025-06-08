/**
 * @author Matej Martinicky
 */

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

/**
 * viewModel for managing ukulele tuning functionality and audio processing
 */
class TunerViewModel(context: Context) : ViewModel() {

    private val recorder = TuningRecorder(context)
    private val detector = TuningDetector()

    val isRecording = MutableStateFlow(false)
    val frequency = MutableStateFlow(0.0)
    val tuneStatus = MutableStateFlow(TuneStatus())

    /**
     * toggles recording on/off
     *
     * starts recording if stopped, stops if currently recording
     */
    fun toggle() {
        if (isRecording.value) {
            isRecording.value = false
        } else {
            start()
        }
    }

    /**
     * starts audio recording and frequency detection loop
     *
     * runs on IO dispatcher to handle audio processing without blocking UI
     * continuously reads audio buffer and analyzes frequency until stopped
     */
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
                            tuneStatus.value = getState(detector.getStringTuningStatus(buffer))
                        }
                        delay(100)
                    }
                } catch (e: Exception) {
                    tuneStatus.value = TuneStatus(TuningStatus.ERROR)
                } finally {
                    recorder.stop()
                }
            }
        }
    }

    /**
     * converts string tuning analysis to tune status
     *
     * @param strings map of ukulele strings and their tuning status
     * @return tune status indicating if any string is properly tuned
     */
    private fun getState(strings: Map<UkuleleString, Boolean>): TuneStatus {
        val inTune = strings.entries.find { it.value }?.key
        return if (inTune != null) {
            TuneStatus(TuningStatus.TUNED, inTune.name)
        } else {
            TuneStatus(TuningStatus.NOT_TUNED)
        }
    }
}