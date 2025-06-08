/**
 * @author from referenced tutorial
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "THIS Is How Easily You Can Record & Play Audio In Android"
 *  https://www.youtube.com/watch?v=4MJFmhcONfI
 *
 * Note this class is more for future extensibility (when adding some storing on Firestone)
 * (but that can get expensive even for small amount of users or even for testing)
 */

package com.example.ukuleletuner2.recorder

import android.content.Context
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

/**
 * Android Audio Recorder via AudioRecorder
 *
 * @param context Context for accessing system services
 */
class AndroidAudioRecorder(private val context: Context) : AudioRecorder {
    private var recorder: MediaRecorder? = null
    private var audioRecord: AudioRecord? = null

    /**
     * note: this check is necessary because:
     * Android S (API 31+) requires context parameter for MediaRecorder constructor,
     * while older versions use the parameterless constructor.
     *(from before mentioned tutorial)
     *
     * @return MediaRecorder instance depending on Android version
     */
    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }
    /**
     * Starts recording audio to the specified output file.
     *
     * Audio settings explanation:
     * - MIC source: Records from device microphone
     * - MPEG_4 format: MP4 container for good compatibility
     * - AAC encoder: High-quality audio compression
     *
     * @param outputFile the file where recorded audio will be saved
     */
    override fun start(outputFile: File) {
        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)

            prepare()
            start()

            recorder = this
        }
    }

    /**
     * Stops the current recording session.
    */
    override fun stop() {
        recorder?.stop()
        recorder?.reset()
        audioRecord = null
    }
}