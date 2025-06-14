/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.SingInScreenPackage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Vertical layout of sign-in elements with proportional spacing
 *
 * @param logoSize size of the app logo
 * @param onSignInClick callback function triggered when sign-in button is pressed
 * @param modifier optional modifier for styling and layout
 */
@Composable
internal fun SignInContent(
    logoSize: Dp,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        AppLogo(size = logoSize)

        Spacer(modifier = Modifier.weight(0.4f))

        SignInPrompt()

        Spacer(modifier = Modifier.weight(0.3f))

        SignInDisclaimer()

        Spacer(modifier = Modifier.weight(0.1f))

        SignInButton(onSignInClick)
    }
}
