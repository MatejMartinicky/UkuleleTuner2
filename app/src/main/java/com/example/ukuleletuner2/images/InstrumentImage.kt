package com.example.ukuleletuner2.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize

@Composable
fun InstrumentImage(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onSizeChanged: (IntSize) -> Unit,
    sizeX: Dp,
    sizeY: Dp
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(sizeX, sizeY)
                .wrapContentWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    val size = layoutCoordinates.size
                    onSizeChanged(size)
                }
        )
    }
}
