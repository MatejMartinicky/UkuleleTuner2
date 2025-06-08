/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.ui.screens.SingInScreenPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.buttons.GoogleLogoButton

/**
 * App logo image with configurable size
 */
@Composable
internal fun AppLogo(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.logo_high_resolution),
        contentDescription = null,
        modifier = Modifier.size(size)
    )
}
/**
 * Main sign-in prompt text with headline styling
 */
@Composable
internal fun SignInPrompt() {
    Text(
        text = stringResource(R.string.sign_in_prompt),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
/**
 * Disclaimer text for sign-in process
 */
@Composable
internal fun SignInDisclaimer() {
    Text(
        text = stringResource(R.string.sign_in_disclaimer),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
/**
 * Google sign-in button
 */
@Composable
internal fun SignInButton(onSignInClick: () -> Unit) {
    GoogleLogoButton(
        text = stringResource(R.string.login_button),
        onClick = onSignInClick
    )
}