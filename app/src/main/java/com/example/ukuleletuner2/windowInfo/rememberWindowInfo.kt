/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Support All Screen Sizes in Jetpack Compose"
 *      https://www.youtube.com/watch?v=HmXgVBys7BU
 *
 * @see source: Mohammed Rashid (codingwithrashid) -
 * "How to Get Screen Orientation in Android Jetpack Compose"
 *      https://codingwithrashid.com/how-to-get-screen-orientation-in-android-jetpack-compose/ (screen rotation code)
 */
package com.example.ukuleletuner2.windowInfo

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

/**
 * creates and remembers window information based on current configuration
 *
 * tracks screen orientation changes and provides window info data
 *
 * @return window info containing current screen orientation
 */
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
/**
 * contains window-related information for responsive layout
 *
 * @param screenOrientation current screen orientation
 */
data class WindowInfo(
    val screenOrientation: WindowOrientation
)
/**
 * represents possible screen orientations
 */
sealed class WindowOrientation {
    object Landscape : WindowOrientation()
    object Portrait : WindowOrientation()
}