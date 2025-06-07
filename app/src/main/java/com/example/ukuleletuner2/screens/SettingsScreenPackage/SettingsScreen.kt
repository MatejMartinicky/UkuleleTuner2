package com.example.ukuleletuner2.screens.SettingsScreenPackage

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToTuner: () -> Unit,
    onNavigateToChords: () -> Unit,
    themeViewModel: ThemeViewModel,
    settingsViewModel: SettingsViewModel
) {
    val windowInfo = rememberWindowInfo()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        settingsViewModel.initializeLanguage(context)
    }

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitSettingsLayout(
                onNavigateToTuner = onNavigateToTuner,
                onNavigateToChords = onNavigateToChords,
                themeViewModel = themeViewModel,
                settingsViewModel = settingsViewModel
            )
        }

        is WindowOrientation.Landscape -> {
            LandscapeSettingsLayout(
                onNavigateToTuner,
                onNavigateToChords,
                themeViewModel,
                settingsViewModel
            )
        }
    }
}


