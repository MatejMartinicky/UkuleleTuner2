/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.tuneDetector

import com.example.ukuleletuner2.fastFurierTransform.FourierTransform
import com.example.ukuleletuner2.widgets.UkuleleString

/**
 * frequency detection for ukulele strings using fast furrier transform.
 */

class TuningDetector() {
    //sample rate: 44.1kHz (normal audio quality)
    //buffer size: 2048 samples
    private val fourierTransform = FourierTransform(44100, 2048)
    //boundary for what is still considered in tune like 10.0Hz
    private val tolerance = 10.0

    /**
     * Analyzes what frequency is dominant in buffer by using furrier transform
     *
     *
     * @param audioBuffer audio buffer from microphone
     * @return dominant frequency in Hz, or 0.0 if no playing
     */
    fun getDetectedFrequency(audioBuffer: ShortArray): Double {
        return fourierTransform.processFFT(audioBuffer)
    }

    /**
     * determines status for strings based on frequency
     *
     * @param audioBuffer raw audio samples from microphone
     * @return Map of UkuleleString to Boolean (true = in tune, false = out of tune)
     */
    fun getStringTuningStatus(audioBuffer: ShortArray): Map<UkuleleString, Boolean> {
        val frequency = getDetectedFrequency(audioBuffer)
        val closestString = findClosestString(frequency)

        return UkuleleString.getAllStrings().associateWith { string ->
            val isClosest = string == closestString
            val difference = kotlin.math.abs(string.frequency - frequency)
            isClosest && difference <= tolerance
        }
    }

    /**
     * finds closes string to this frequency
     *
     * compares dominant frequency from fast furrier transform to identify witch string is user
     * trying to tune
     *
     * @param frequency frequency detected by fast furrier transform
     * @return returns closest sting, or null if noting is detected
     */
    private fun findClosestString(frequency: Double): UkuleleString? {
        //finds witch ukulele string has frequency difference (string.frequency - frequency)
        // closest to detected frequency
        return UkuleleString.getAllStrings().minByOrNull { string ->
            kotlin.math.abs(string.frequency - frequency)
        }
    }
}