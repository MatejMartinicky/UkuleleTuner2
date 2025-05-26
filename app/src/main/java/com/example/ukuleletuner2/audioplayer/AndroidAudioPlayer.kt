package com.example.ukuleletuner2.audioplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

//https://www.youtube.com/watch?v=4MJFmhcONfI

class AndroidAudioPlayer(private val context: Context) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(file: File) {
        MediaPlayer.create(context, file.toUri())?.apply {
            start()
        }
    }
    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}