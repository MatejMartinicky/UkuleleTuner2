/**
 * @author Matej Martinicky (but lot is based on tutorial)
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "THIS Is How Easily You Can Record & Play Audio In Android"
 *  https://www.youtube.com/watch?v=4MJFmhcONfI
 */
package com.example.ukuleletuner2.recorder

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.example.ukuleletuner2.R


/**
 * audio recorder designed for real-time tuning analysis and tune detection
 */
class TuningRecorder(private val context: Context) {
    //sample rate - 44.1kHz (this influences quality)
    private val sampleRate = 44100
    private var audioRecord: AudioRecord? = null
    private var isRecording = false
    //buffer size for audio data capture
    //sampleRate - 44.1kHz sample rate (set above)
    //AudioFormat.CHANNEL_IN_MONO - single channel (microphone input)
    //AudioFormat.ENCODING_PCM_16BIT - 16-bit samples (solid quality)
    private val bufferSize = AudioRecord.getMinBufferSize(
        sampleRate,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )
    /**
     * starts real-time audio getting
     *
     * -checks for audio recording permission
     * -if non throws
     * -sets optimal settings for musical pitch detection
     * -starts recording
     * -switches flag
     *
     * @throws SecurityException if RECORD_AUDIO permission is not granted
     */
    fun start() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            throw SecurityException(context.getString(R.string.exception_no_mic_permissions))
        }

        //initialize AudioRecord with tuning-optimized settings
        //mediaRecorder.AudioSource.MIC - Use device microphone
        //sampleRate - 44.1kHz for musical accuracy
        //audioFormat.CHANNEL_IN_MONO - Single channel input
        //audioFormat.ENCODING_PCM_16BIT - 16-bit PCM encoding
        //bufferSize * 2 - Double buffer size for stability
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize * 2
        )
        audioRecord?.startRecording()
        isRecording = true
    }
    /**
     * Stops audio capture and releases system resources.
     */
    fun stop() {
        isRecording = false
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }

    /**
     *
     *reads raw audio samples into the provided buffer.
     *
     * called in a loop by performed an analysis algorithms to continuously analyze incoming audio.
    */
    fun read(buffer: ShortArray): Int {
        //return  is no not recording or not initialized
        if (!isRecording || audioRecord == null) return 0

        return try {
            //put sample into buffer
            //!! - not-null assertion operator
            //  promises kotlin that even if audio recorder is nullable here it will exist
            audioRecord!!.read(buffer, 0, buffer.size)
        } catch (e: SecurityException) {
            //return 0 when premonition taken away during recording
            0
        }
    }
}

