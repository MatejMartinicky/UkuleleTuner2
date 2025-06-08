/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.widgets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ukuleletuner2.recorder.TuningRecorder
import com.example.ukuleletuner2.tuneDetector.TuningDetector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * viewmodel for managing tuning functionality in widget
 *
 * handles audio recording and frequency detection for widget interface
 *
 * @param tuningRecorder audio recorder instance
 */
class TuningWidgetViewModel(
    private val tuningRecorder: TuningRecorder
) : ViewModel() {

    private val tuningDetector = TuningDetector()

    var isListening = MutableStateFlow(false)
    var frequency = MutableStateFlow(0.0)
    var tuningStatus = MutableStateFlow<Map<UkuleleString, Boolean>>(emptyMap())

    /**
     * starts audio recording and frequency analysis
     *
     * runs continuous loop to read audio buffer and detect frequencies
     * operates on IO dispatcher to avoid blocking UI
     */
    fun startTuning() {
        if (isListening.value) return

        isListening.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val buffer = ShortArray(2048)
            tuningRecorder.start()

            while (isListening.value) {
                val read = tuningRecorder.read(buffer)
                if (read > 0) {
                    frequency.value = tuningDetector.getDetectedFrequency(buffer)
                    tuningStatus.value = tuningDetector.getStringTuningStatus(buffer)
                }
                delay(100)
            }

            tuningRecorder.stop()
        }
    }

    /**
     * stops audio recording and frequency analysis
     */
    fun stopTuning() {
        isListening.value = false
    }
}