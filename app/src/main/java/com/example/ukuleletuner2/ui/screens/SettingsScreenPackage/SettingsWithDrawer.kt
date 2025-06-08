/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.SettingsScreenPackage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.ukuleletuner2.ui.screens.TunerScreenPackage.DrawerContent
import kotlinx.coroutines.launch
/**
 * Scaffold wrapper for settings screen with navigation drawer
 *
 * @param onNavigateToTuner callback to navigate to tuner screen
 * @param onNavigateToChords callback to navigate to chords screen
 * @param content composable content to display in the main area
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsWithDrawer(
    onNavigateToTuner: () -> Unit,
    onNavigateToChords: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    onNavigateToSettings = { },
                    onNavigateToChords = onNavigateToChords,
                    onNavigateToHome = onNavigateToTuner,
                    onCloseDrawer = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                SettingsTopBar(
                    nNavigateToTuner = onNavigateToTuner,
                    onNavigateToChords = onNavigateToChords,
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}
