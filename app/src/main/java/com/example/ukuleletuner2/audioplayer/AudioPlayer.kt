package com.example.ukuleletuner2.audioplayer

import java.io.File

//https://www.youtube.com/watch?v=4MJFmhcONfI

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}