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
 * circular flag image component for language selection
 *
 * @param flagPainter image painter for the country flag
 * @param contentDescription accessibility description for the flag image
 * @param size diameter of the circular flag image (default 120dp)
 * @param onClick optional callback function triggered when the flag is clicked
 */
@Composable
fun FlagImage(
    flagPainter: Painter,
    contentDescription: String? = null,
    size: Dp = 120.dp,
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
            painter = flagPainter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

