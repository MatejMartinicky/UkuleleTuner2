/**
 * @author Matej Martinicky but lot from tutorial
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Support All Screen Sizes in Jetpack Compose"
 *       https://www.youtube.com/watch?v=HmXgVBys7BU
 * (screen rotation)
 */
package com.example.ukuleletuner2.ui.screens.WelcomeScreenPackage

import androidx.compose.runtime.Composable
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo
/**
 * Welcome screen that adapts layout based on device orientation
 *
 * @param onNavigateToLoginScreen callback to navigate to login/sign-in screen
 */
@Composable
fun WelcomeScreen(
    onNavigateToLoginScreen: () -> Unit
) {
    val windowInfo = rememberWindowInfo()

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitWelcomeLayout(onNavigateToLoginScreen)
        }

        is WindowOrientation.Landscape -> {
            LandscapeWelcomeLayout(onNavigateToLoginScreen)
        }
    }
}


