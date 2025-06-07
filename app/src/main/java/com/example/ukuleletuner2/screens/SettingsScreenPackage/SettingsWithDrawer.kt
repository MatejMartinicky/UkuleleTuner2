package com.example.ukuleletuner2.screens.SettingsScreenPackage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.ukuleletuner2.screens.TunerScreenPackage.DrawerContent
import com.example.ukuleletuner2.screens.TunerScreenPackage.TunerTopBar
import kotlinx.coroutines.launch

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
        ) {
            paddingValues ->
            content(paddingValues)
        }
    }
}
