/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.ChordsScreenPackage

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
 * scaffold layout with top bar and navigation drawer for the chords screen
 *
 * @param onNavigateToSettings callback function for navigating to settings screen
 * @param onNavigateToChords callback function for navigating to chords screen
 * @param onNavigateToHome callback function for navigating to home/tuner screen
 * @param content composable content to display in the main area with padding values
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ChordsWithTopBar(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit,
    onNavigateToHome: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    onNavigateToSettings = onNavigateToSettings,
                    onNavigateToChords = onNavigateToChords,
                    onNavigateToHome = onNavigateToHome,
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
                ChordsTopBar(
                    onNavigateToSettings = onNavigateToSettings,
                    onOpenDrawer = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
            content = content
        )
    }
}