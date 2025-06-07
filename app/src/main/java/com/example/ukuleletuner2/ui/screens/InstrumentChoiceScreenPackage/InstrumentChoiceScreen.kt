package com.example.ukuleletuner2.ui.screens.InstrumentChoiceScreenPackage

import androidx.compose.runtime.Composable
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)

@Composable
fun InstrumentChoiceScreen(onNavigateToTunerScreen: () -> Unit) {
    val windowInfo = rememberWindowInfo()

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitInstrumentLayout(onNavigateToTunerScreen)
        }

        is WindowOrientation.Landscape -> {
            LandscapeInstrumentLayout(onNavigateToTunerScreen)
        }
    }
}


