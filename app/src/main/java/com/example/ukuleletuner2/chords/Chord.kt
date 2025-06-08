/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) - "THIS Is How Easily You Can Record & Play Audio In Android"
 *      https://www.youtube.com/watch?v=4MJFmhcONfI
 */

package com.example.ukuleletuner2.chords

/**
 * Data class that represents chord that's sound can be played
 *
 * @param id identifier of the chord
 * @param name name of this chord
 * @param image ID of image resource that shows users how to play this chord
 * @param audioFileName  ID of audio resource to be played
 */
data class Chord(
    val id: Int,
    val name: Int,
    val image: Int,
    val audioFileName: Int
)
