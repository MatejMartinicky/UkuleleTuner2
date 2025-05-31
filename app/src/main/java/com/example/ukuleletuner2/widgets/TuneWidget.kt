package com.example.ukuleletuner2.widgets

import android.R
import android.content.Context
import androidx.glance.text.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.FontWeight
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.ukuleletuner2.widgets.TuneWidget

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
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally
    )
    {
        Text(
            text = "0",
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 26.sp
            )
        )
        Button(text = "Tune",
            onClick = {}
        )
    }
}