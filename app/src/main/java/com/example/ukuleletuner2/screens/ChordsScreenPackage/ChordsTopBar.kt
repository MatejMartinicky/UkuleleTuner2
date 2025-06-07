package com.example.ukuleletuner2.screens.ChordsScreenPackage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.ukuleletuner2.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChordsTopBar(
    onNavigateToSettings: () -> Unit,
    onOpenDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.chords_screen_title))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationIcon = {
            IconButton(onClick = onOpenDrawer) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu_content_description)
                )
            }
        },
        actions = {
            IconButton(onClick = onNavigateToSettings) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.settings_content_description)
                )
            }
        }
    )
}
