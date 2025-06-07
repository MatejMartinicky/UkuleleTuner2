package com.example.ukuleletuner2.screens.WelcomeScreenPackage

import androidx.compose.runtime.Composable
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)

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


