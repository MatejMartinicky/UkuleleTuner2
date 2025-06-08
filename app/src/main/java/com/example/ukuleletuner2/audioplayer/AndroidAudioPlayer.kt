/**
 * Author: Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) - "THIS Is How Easily You Can Record & Play Audio In Android"
 *      https://www.youtube.com/watch?v=4MJFmhcONfI
 */

package com.example.ukuleletuner2.audioplayer

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

/**
 * Implementation of AudioPlayer using MediaPlayer
 * This player has hooks that can be executed before or after playback making it more versatile

 */
class AndroidAudioPlayer(private val context: Context) : AudioPlayer {
    private var player: MediaPlayer? = null
    private var beforeHook: (() -> Unit)? = null
    private var afterHook: (() -> Unit)? = null
    /**
     * Sets hook to be executed when audio paying starts
     *
     * @param hook hook to be executed
     */
    fun setBeforeHook(hook: () -> Unit) {
        beforeHook = hook
    }
    /**
     * plays an audio resource
     *
     * -before hook gets executed
     * -if some audio is playing it gets stopped
     * -attempts to create new MediaPlayer
     * -on this newly created MediaPlayer instance sets on setOnCompletionListener
     *      that will list when audio playback is done
     *      and when it is done it invokes afterHook
     *      and calls stop
     * - stats playing audio
     * - if anything fails catches and exception and invokes after hook
     *      (user will here that it is not playing, so not rethrown)
     *
     * @param resourceId resource ID of file to play (IDs are passed as Ints)
     */
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
    /**
     * Plays an audio file from the file system.
     *
     * Identical behavior to playResource() but accepts a File instead of resource ID.
     * Converts the file to URI before creating MediaPlayer.
     *
     * @param file the audio file to play
     * @see playResource for detailed explanation
     */
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
    /**
     * Stops media player
     *
     * -if there is media player to stop (it isn't null) it stops it
     * -releases resources associated with it
     * -and sets this attribute to null
     */
    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
    /**
     * Sets hook to be executed when audio paying ends
     *
     * @param hook hook to be executed
     */
    fun setAfterHook(hook: () -> Unit) {
        afterHook = hook
    }
}