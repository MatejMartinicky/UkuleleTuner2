/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
/**
 * circular theme preview image component for color theme selection
 *
 * @param themePainter image painter for the theme preview
 * @param contentDescription accessibility description for the theme image
 * @param size diameter of the circular theme preview (default 60dp)
 * @param onClick optional callback function triggered when the theme is clicked
 */
@Composable
fun ThemeImage(
    themePainter: Painter,
    contentDescription: String? = null,
    size: Dp = 60.dp,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .size(size)
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Image(
            painter = themePainter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}