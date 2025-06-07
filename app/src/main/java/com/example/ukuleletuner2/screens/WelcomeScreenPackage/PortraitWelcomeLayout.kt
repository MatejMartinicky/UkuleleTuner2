package com.example.ukuleletuner2.screens.WelcomeScreenPackage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
internal fun PortraitWelcomeLayout(
    onNavigateToLoginScreen: () -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        WelcomeImageWithGradient()
        WelcomeContent(
            onNavigateToLoginScreen = onNavigateToLoginScreen,
            modifier = Modifier.fillMaxSize()
        )
    }
}