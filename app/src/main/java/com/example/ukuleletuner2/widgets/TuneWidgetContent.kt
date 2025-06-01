package com.example.ukuleletuner2.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import com.example.ukuleletuner2.widgets.UkuleleString

//separate into separat file
@Composable
fun TuneWidgetContent() {
    val context = LocalContext.current
    val ukuleleStrings = UkuleleString.getAllStrings()
    val BUTTON_SIZE = 80
    val BUTTON_PADDING = 4

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(8.dp),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
    ) {
        Row(
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            TuneButton(
                tone = ukuleleStrings[0].getDisplayName(context),
                inTune = false,
                buttonSize = BUTTON_SIZE,
                buttonColor = ukuleleStrings[0].color,
                modifier = GlanceModifier.padding(BUTTON_PADDING.dp),
                onClick = { /*TODO*/ }
            )

            TuneButton(
                tone = ukuleleStrings[1].getDisplayName(context),
                inTune = false,
                buttonSize = BUTTON_SIZE,
                buttonColor = ukuleleStrings[1].color,
                modifier = GlanceModifier.padding(BUTTON_PADDING.dp),
                onClick = { /*TODO*/ }
            )
        }

        Row(
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            TuneButton(
                tone = ukuleleStrings[2].getDisplayName(context),
                inTune = false,
                buttonSize = BUTTON_SIZE,
                buttonColor = ukuleleStrings[2].color,
                modifier = GlanceModifier.padding(BUTTON_PADDING.dp),
                onClick = { /*TODO*/ }
            )

            TuneButton(
                tone = ukuleleStrings[3].getDisplayName(context),
                inTune = false,
                buttonSize = BUTTON_SIZE,
                buttonColor = ukuleleStrings[3].color,
                modifier = GlanceModifier.padding(BUTTON_PADDING.dp),
                onClick = { /*TODO*/ }
            )
        }
    }
}
