/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.SettingsScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel
/**
 * landscape-oriented layout for settings screen with two-column arrangement
 *
 * @param onNavigateToTuner callback function for navigating to tuner screen
 * @param onNavigateToChords callback function for navigating to chords screen
 * @param themeViewModel view model for managing theme selection and changes
 * @param settingsViewModel view model for managing general settings including language
 */
@Composable
internal fun LandscapeSettingsLayout(
    onNavigateToTuner: () -> Unit,
    onNavigateToChords: () -> Unit,
    themeViewModel: ThemeViewModel,
    settingsViewModel: SettingsViewModel
) {
    SettingsWithDrawer(
        onNavigateToTuner = onNavigateToTuner,
        onNavigateToChords = onNavigateToChords
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item { General(themeViewModel, settingsViewModel) }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item { Info() }
                item { Contact() }
            }
        }
    }
}