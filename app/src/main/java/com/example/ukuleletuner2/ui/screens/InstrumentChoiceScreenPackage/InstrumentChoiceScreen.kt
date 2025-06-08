/**
 * @author based on tutorial
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Support All Screen Sizes in Jetpack Compose"
 *  https://www.youtube.com/watch?v=HmXgVBys7BU
 *  (screen rotation)
 */

package com.example.ukuleletuner2.ui.screens.InstrumentChoiceScreenPackage

import androidx.compose.runtime.Composable
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo
/**
 * instrument selection screen with adaptive layout for different orientations
 *
 * @param onNavigateToTunerScreen callback function for navigating to tuner screen after instrument selection
 */
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


