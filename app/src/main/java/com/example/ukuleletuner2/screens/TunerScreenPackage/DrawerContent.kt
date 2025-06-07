package com.example.ukuleletuner2.screens.TunerScreenPackage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.navigationDrawer.DrawerBody
import com.example.ukuleletuner2.navigationDrawer.DrawerHeader
import com.example.ukuleletuner2.navigationDrawer.MenuItem
import com.example.ukuleletuner2.navigationDrawer.MenuItems

@Composable
fun DrawerContent(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit,
    onNavigateToHome: () -> Unit,
    onCloseDrawer: () -> Unit
) {
    DrawerHeader()
    DrawerBody(
        items = listOf(
            MenuItem(
                id = MenuItems.Home,
                title = stringResource(R.string.drawer_home),
                contentDescription = stringResource(R.string.drawer_home_description),
                icon = Icons.Default.Home
            ),
            MenuItem(
                id = MenuItems.Chords,
                title = stringResource(R.string.drawer_chords),
                contentDescription = stringResource(R.string.drawer_chords_description),
                icon = ImageVector.vectorResource(id = R.drawable.baseline_music_note_24)
            ),
            MenuItem(
                id = MenuItems.Settings,
                title = stringResource(R.string.drawer_settings),
                contentDescription = stringResource(R.string.drawer_settings),
                icon = Icons.Default.Settings
            ),
        ),
        onItemClick = { menuItem ->
            when (menuItem.id) {
                is MenuItems.Settings -> {
                    onNavigateToSettings()
                    onCloseDrawer()
                }

                is MenuItems.Home -> {
                    onNavigateToHome()
                    onCloseDrawer()
                }

                is MenuItems.Chords -> {
                    onNavigateToChords()
                    onCloseDrawer()
                }
            }
        }
    )
}