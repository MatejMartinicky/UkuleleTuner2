/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) - "THIS Is How Easily You Can Record & Play Audio In Android"
 *      https://www.youtube.com/watch?v=4MJFmhcONfI
 */
package com.example.ukuleletuner2.audioplayer

import java.io.File

/**
 * Class that provides interface of how AudioPlayer should look
 */
interface AudioPlayer {
    /**
     * plays audio file
     *
     * @param file file to play
     */
    fun playFile(file: File)
    /**
     * stops currently playing audio and releases resources of player
     */
    fun stop()
}