package com.example.ukuleletuner2.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text


@Composable
fun TuneButton(
    tone: String,
    inTune: Boolean,
    onClick: () -> Unit
) {
    val background = if (inTune) {
        Color.Green
    } else {
        Color.Red
    }

    Box(
        modifier = GlanceModifier
            .size(80.dp)
            .background(background)
            .clickable(onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = tone
        )
    }
}