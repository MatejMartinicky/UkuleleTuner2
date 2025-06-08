/**
 * @author from referenced tutorial
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "THIS Is How Easily You Can Record & Play Audio In Android"
 *  https://www.youtube.com/watch?v=4MJFmhcONfI
 */
package com.example.ukuleletuner2.recorder

import java.io.File

/**
 * Interface for how AudioRecorder should look like
 */
interface AudioRecorder {
    /**
     * Starts recording audio to the specified file.
     *
     * Begins capturing audio from the device's microphone and saves it to the
     * provided output file. The file format and quality depend on the implementation.
     *
     * @param outputFile the file where the recorded audio will be saved
     * @throws Exception if recording cannot be started (permissions, file access, etc.)
     */
    fun start(outputFile: File)
    /**
     * Stops the current recording.
     */
fun stop()
}