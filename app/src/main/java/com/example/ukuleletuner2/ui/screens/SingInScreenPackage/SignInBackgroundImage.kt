/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.SingInScreenPackage

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
/**
 * Displays background image with crop scaling for sign-in screen
 *
 * @param painter image painter to display
 * @param modifier optional modifier for styling and layout
 */
@Composable
internal fun SignInBackgroundImage(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}
