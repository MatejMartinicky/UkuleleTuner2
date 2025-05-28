package com.example.ukuleletuner2.audioplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File
import javax.security.auth.callback.Callback

//https://www.youtube.com/watch?v=4MJFmhcONfI

class AndroidAudioPlayer(private val context: Context) : AudioPlayer {

    private var player: MediaPlayer? = null
    private var BeforeHook : (() -> Unit)? = null;
    private var AfterHook : (() -> Unit)? = null;


    fun setBeforeHook(hook: () -> Unit) {
        BeforeHook = hook;
    }

    override fun playFile(file: File) {
        BeforeHook?.invoke()

        MediaPlayer.create(context, file.toUri())?.apply {
            setOnCompletionListener {
                AfterHook?.invoke()
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
        AfterHook = hook;
    }

}