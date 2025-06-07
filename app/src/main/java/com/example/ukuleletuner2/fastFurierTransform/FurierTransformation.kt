package com.example.ukuleletuner2.fastFurierTransform

import kotlin.math.sqrt

//PROBLABLY LIKE RECOMANDED BY THAT CODE ASISTENT IT CAME WITH IDK SOURCE!!!!
//OR FROM SOME GITHUB IDK
class FourierTransform(private val sampleRate: Int, private val bufferSize: Int) {
    fun processFFT(audioData: ShortArray): Double {
        val real = DoubleArray(bufferSize)
        val imaginary = DoubleArray(bufferSize)
        val magnitude = DoubleArray(bufferSize / 2)

        // Convert short array to double for FFT processing
        for (i in audioData.indices) {
            real[i] = audioData[i].toDouble()
            imaginary[i] = 0.0
        }

        // Perform FFT (naive implementation, use a library like JTransforms for real use)
        fft(real, imaginary)

        // Calculate magnitude and find the dominant frequency
        var maxIndex = 0
        var maxMagnitude = 0.0
        for (i in magnitude.indices) {
            magnitude[i] = sqrt(real[i] * real[i] + imaginary[i] * imaginary[i])
            if (magnitude[i] > maxMagnitude) {
                maxMagnitude = magnitude[i]
                maxIndex = i
            }
        }

        // Convert index to frequency
        return maxIndex * sampleRate / bufferSize.toDouble()
    }

    private fun fft(real: DoubleArray, imaginary: DoubleArray) {
        val n = real.size
        if (n == 1) return

        val evenReal = DoubleArray(n / 2)
        val evenImag = DoubleArray(n / 2)
        val oddReal = DoubleArray(n / 2)
        val oddImag = DoubleArray(n / 2)

        for (i in 0 until n / 2) {
            evenReal[i] = real[i * 2]
            evenImag[i] = imaginary[i * 2]
            oddReal[i] = real[i * 2 + 1]
            oddImag[i] = imaginary[i * 2 + 1]
        }

        fft(evenReal, evenImag)
        fft(oddReal, oddImag)

        for (k in 0 until n / 2) {
            val tReal =
                oddReal[k] * kotlin.math.cos(2 * Math.PI * k / n) - oddImag[k] * kotlin.math.sin(2 * Math.PI * k / n)
            val tImag =
                oddReal[k] * kotlin.math.sin(2 * Math.PI * k / n) + oddImag[k] * kotlin.math.cos(2 * Math.PI * k / n)

            real[k] = evenReal[k] + tReal
            imaginary[k] = evenImag[k] + tImag
            real[k + n / 2] = evenReal[k] - tReal
            imaginary[k + n / 2] = evenImag[k] - tImag
        }
    }
}