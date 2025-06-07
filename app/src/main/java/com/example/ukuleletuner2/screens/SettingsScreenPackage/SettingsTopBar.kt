package com.example.ukuleletuner2.screens.SettingsScreenPackage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
internal fun SettingsTopBar(
    nNavigateToTuner: () -> Unit,
    onNavigateToChords: () -> Unit,
    onOpenDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.settings_screen_title))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        navigationIcon = {
            IconButton(
                onClick = onOpenDrawer
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu_content_description),
                )
            }
        },
        actions = { /*todo*/ }
    )
}