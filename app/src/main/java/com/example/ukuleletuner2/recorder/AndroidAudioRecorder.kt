package com.example.ukuleletuner2.recorder

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder

class AndroidAudioRecorder : AudioRecorder {
    private val sampleRate = 44100
    private val bufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
    private var audioRecord: AudioRecord? = null

    override fun start() {
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        ).apply {
            startRecording()
        }
    }

    override fun stop() {
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }

    override fun read(buffer: ShortArray): Int {
        return audioRecord?.read(buffer, 0, buffer.size) ?: 0
    }
}