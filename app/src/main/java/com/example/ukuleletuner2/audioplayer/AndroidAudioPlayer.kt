package com.example.ukuleletuner2.audioplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

//https://www.youtube.com/watch?v=4MJFmhcONfI

class AndroidAudioPlayer(private val context: Context) : AudioPlayer {

    private var player: MediaPlayer? = null
    private var beforeHook: (() -> Unit)? = null
    private var afterHook: (() -> Unit)? = null


    fun setBeforeHook(hook: () -> Unit) {
        beforeHook = hook
    }

    fun playResource(resourceId: Int) {
        beforeHook?.invoke()

        stop()

        try {
            player = MediaPlayer.create(context, resourceId)?.apply {
                setOnCompletionListener {
                    afterHook?.invoke()
                    stop()
                }
                start()
            }
        } catch (e: Exception) {
            afterHook?.invoke()
        }
    }


    override fun playFile(file: File) {
        beforeHook?.invoke()

        stop()

        MediaPlayer.create(context, file.toUri())?.apply {
            setOnCompletionListener {
                afterHook?.invoke()
                stop()
            }

            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    fun setAfterHook(hook: () -> Unit) {
        afterHook = hook
    }
}