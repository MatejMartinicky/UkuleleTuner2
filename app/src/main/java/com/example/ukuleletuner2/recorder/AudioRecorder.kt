package com.example.ukuleletuner2.recorder

import java.io.File

//https://www.youtube.com/watch?v=4MJFmhcONfI

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
    fun read(buffer: ShortArray): Int
}