/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.size
import androidx.glance.text.Text


/**
 * circular button with tuning status indicator for ukulele strings
 *
 * displays note name with colored ring indicating tuning status
 * green ring when in tune, red when out of tune
 *
 * @param tone note name to display (C, G, E, A)
 * @param inTune whether string is properly tuned
 * @param buttonSize size of the main button in dp
 * @param buttonColor color of the main button
 * @param modifier optional glance modifier
 * @param onClick action to execute when button is pressed
 */
@Composable
fun TuneButton(
    tone: String,
    inTune: Boolean,
    buttonSize: Int,
    buttonColor: Color,
    modifier: GlanceModifier = GlanceModifier,
    onClick: () -> Unit
) {
    val INDICATOR_SIZE_PLUS = 10
    val indicatorSize = buttonSize + INDICATOR_SIZE_PLUS
    val background = if (inTune) Color.Green else Color.Red

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = GlanceModifier.size(indicatorSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = GlanceModifier
                    .size(indicatorSize.dp)
                    .cornerRadius((indicatorSize / 2).dp)
                    .background(background)
            ) {}

            Box(
                modifier = GlanceModifier
                    .size(buttonSize.dp)
                    .cornerRadius((buttonSize / 2).dp)
                    .background(buttonColor)
                    .clickable(onClick),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tone
                )
            }
        }
    }
}