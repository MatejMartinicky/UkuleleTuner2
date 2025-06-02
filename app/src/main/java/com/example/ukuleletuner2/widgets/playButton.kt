package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.example.ukuleletuner2.R

@Composable
fun PlayButton(
    size: Dp,
    isListening: Boolean,
    context: Context,
    onStart: Action,
    onStop: Action
) {
    val padding = 4.dp

    Column {
        Box(
            modifier = GlanceModifier
                .size(size)
                .cornerRadius(size / 2)
                .background(MaterialTheme.colorScheme.onBackground)
                .clickable(
                    if (isListening) onStop else onStart
                )
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                provider = ImageProvider(
                    if (isListening) {
                        R.drawable.stop_arrow
                    } else {
                        R.drawable.play_arrow
                    }
                ),
                contentDescription = if (isListening) "Stop" else "Play",
                modifier = GlanceModifier.size(size - padding * 2)
            )
        }
    }
}