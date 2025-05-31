package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import com.example.ukuleletuner2.R

//https://www.youtube.com/watch?v=bhrN7yFG0D4 (butt like really old version)

object TuneWidget: GlanceAppWidget() {
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent {
            TuneWidgetContent()
        }
    }
}
class TuneWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TuneWidget
}

//separate into separat file
@Composable
fun TuneWidgetContent() {
    val context = LocalContext.current

    // Separate out later
    val ukuleleStrings = listOf(
        context.getString(R.string.g4) to 392.00,
        context.getString(R.string.c4) to 261.63,
        context.getString(R.string.e4) to 329.63,
        context.getString(R.string.a4) to 440.00
    )

    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
      ) {

        Row(
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            TuneButton(
                tone = ukuleleStrings[0].first,
                inTune = false,
                onClick = {
                    //TODO
                }
            )

            TuneButton(
                tone = ukuleleStrings[1].first,
                inTune = false,
                onClick = {
                    //TODO
                }
            )
        }

        Row(
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            TuneButton(
                tone = ukuleleStrings[2].first,
                inTune = false,
                onClick = {
                    //TODO
                }
            )

            TuneButton(
                tone = ukuleleStrings[3].first,
                inTune = false,
                onClick = {
                    //TODO
                }
            )
        }
    }
}
