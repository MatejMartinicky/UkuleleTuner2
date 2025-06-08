/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.WelcomeScreenPackage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
/**
 * Landscape layout with side-by-side image and content
 *
 * @param onNavigateToLoginScreen callback to navigate to login/sign-in screen
 */
@Composable
internal fun LandscapeWelcomeLayout(
    onNavigateToLoginScreen: () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        WelcomeImage(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )

        WelcomeContent(
            onNavigateToLoginScreen = onNavigateToLoginScreen,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}
