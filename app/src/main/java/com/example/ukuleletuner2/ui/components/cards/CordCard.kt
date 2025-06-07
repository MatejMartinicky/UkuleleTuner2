package com.example.ukuleletuner2.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//https://www.youtube.com/watch?v=KPVoQjwmWX4 //creating card
//https://stackoverflow.com/questions/75950471/how-to-make-rounded-corner-with-shadow-in-jetpack-compose
@Composable
fun ChordCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    isPlaying: Boolean = false,
    onPlayed: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val shadowColor = MaterialTheme.colorScheme.outline
    MaterialTheme.colorScheme.scrim
    val onSurfaceColor = MaterialTheme.colorScheme.surfaceContainer

    val glowLayers = 6
    val glowSpacing = 2.dp
    val glowExpansion = 4.dp
    val baseAlpha = 0.4f

    val playingShadowElevation = 32.dp
    val normalShadowElevation = 8.dp
    val cornerRadius = 16.dp
    val cardCornerRadius = 15.dp
    val cardElevation = 5.dp

    val cardHeight = 200.dp
    val contentPadding = 12.dp
    16.sp

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onPlayed() }
            .drawBehind {
                if (isPlaying) {
                    for (i in 1..glowLayers) {
                        drawRoundRect(
                            color = shadowColor.copy(alpha = baseAlpha / i),
                            topLeft = Offset(
                                -(i * glowSpacing.value).dp.toPx(),
                                -(i * glowSpacing.value).dp.toPx()
                            ),
                            size = Size(
                                size.width + (i * glowExpansion.value).dp.toPx(),
                                size.height + (i * glowExpansion.value).dp.toPx()
                            ),
                            cornerRadius = CornerRadius(cornerRadius.toPx()),
                        )
                    }
                }
            }
            .shadow(
                elevation = if (isPlaying) playingShadowElevation else normalShadowElevation,
                shape = RoundedCornerShape(cornerRadius),
                spotColor = if (isPlaying) shadowColor else Color.Transparent,
                ambientColor = if (isPlaying) shadowColor else Color.Black
            ),
        shape = RoundedCornerShape(cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
    ) {
        Box(modifier = Modifier.height(cardHeight)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium, //change
                    color = onSurfaceColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}