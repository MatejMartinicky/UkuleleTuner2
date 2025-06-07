package com.example.ukuleletuner2.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.size

@Composable
fun StringIndicator(
    color: Color,
    inTune: Boolean,
    size: Dp,
    stringThickness: Dp,
    modifier: GlanceModifier = GlanceModifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = GlanceModifier
                .size(
                    width = if (inTune) (stringThickness * 2) else stringThickness,
                    height = size
                )
                .background(color)
        ) {}
    }
}