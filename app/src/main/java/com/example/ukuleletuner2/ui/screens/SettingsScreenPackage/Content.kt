package com.example.ukuleletuner2.ui.screens.SettingsScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.cards.LanguageSelector
import com.example.ukuleletuner2.ui.components.cards.ThemeCard
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel


@Composable
internal fun General(
    themeViewModel: ThemeViewModel,
    settingsViewModel: SettingsViewModel
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(R.string.settings_general),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        ThemeCard(themeViewModel = themeViewModel)
        LanguageSelector(settingsViewModel = settingsViewModel)
    }
}

@Composable
internal fun Info() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(R.string.settings_info),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Column {
            Text(
                text = stringResource(R.string.author),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.author_name),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Thin
            )
        }

        Column {
            Text(
                text = stringResource(R.string.version),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.version_version),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Thin
            )
        }
    }
}

@Composable
internal fun Contact() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(R.string.contact),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )

        Column {
            Text(
                text = stringResource(R.string.email),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.email_address),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Thin
            )
        }
    }
}