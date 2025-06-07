package com.example.ukuleletuner2.windowInfo

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

//https://www.youtube.com/watch?v=HmXgVBys7BU (execution)
//https://codingwithrashid.com/how-to-get-screen-orientation-in-android-jetpack-compose/ (screen rotation code)

@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    val orientation = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        WindowOrientation.Landscape
    } else {
        WindowOrientation.Portrait
    }

    return WindowInfo(screenOrientation = orientation)
}

data class WindowInfo(
    val screenOrientation: WindowOrientation
)

sealed class WindowOrientation {
    object Landscape : WindowOrientation()
    object Portrait : WindowOrientation()
}