package com.example.ukuleletuner2.ui.screens.SettingsScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel

@Composable
internal fun PortraitSettingsLayout(
    onNavigateToTuner: () -> Unit,
    onNavigateToChords: () -> Unit,
    themeViewModel: ThemeViewModel,
    settingsViewModel: SettingsViewModel
) {
    SettingsWithDrawer(
        onNavigateToTuner = onNavigateToTuner,
        onNavigateToChords = onNavigateToChords
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item { General(themeViewModel, settingsViewModel) }
            item {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp),
                    color = MaterialTheme.colorScheme.outline
                )
            }
            item {
                Info()
            }
            item {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp),
                    color = MaterialTheme.colorScheme.outline
                )
            }
            item {
                Contact()
            }
        }
    }
}
