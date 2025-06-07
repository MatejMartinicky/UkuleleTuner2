package com.example.ukuleletuner2.ui.screens.TunerScreenPackage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TunerTopBar(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit,
    onOpenDrawer: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.tuner_screen_title)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            Text(
                stringResource(R.string.chords_navigation_text),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { onNavigateToChords() }
                    .padding(16.dp)
            )
            IconButton(
                onClick = onNavigateToSettings
            ) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = onOpenDrawer
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu_content_description),
                )
            }
        }
    )
}