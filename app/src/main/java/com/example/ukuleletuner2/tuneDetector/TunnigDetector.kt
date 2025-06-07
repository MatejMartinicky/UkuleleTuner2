package com.example.ukuleletuner2.tuneDetector

import com.example.ukuleletuner2.fastFurierTransform.FourierTransform
import com.example.ukuleletuner2.widgets.UkuleleString


class TuningDetector() {
    private val fourierTransform = FourierTransform(44100, 2048)
    private val tolerance = 10.0

    fun getDetectedFrequency(audioBuffer: ShortArray): Double {
        return fourierTransform.processFFT(audioBuffer)
    }

    fun getStringTuningStatus(audioBuffer: ShortArray): Map<UkuleleString, Boolean> {
        val frequency = getDetectedFrequency(audioBuffer)
        val closestString = findClosestString(frequency)

        return UkuleleString.getAllStrings().associateWith { string ->
            val isClosest = string == closestString
            val difference = kotlin.math.abs(string.frequency - frequency)
            isClosest && difference <= tolerance
        }
    }

    private fun findClosestString(frequency: Double): UkuleleString? {
        return UkuleleString.getAllStrings().minByOrNull { string ->
            kotlin.math.abs(string.frequency - frequency)
        }
    }
}