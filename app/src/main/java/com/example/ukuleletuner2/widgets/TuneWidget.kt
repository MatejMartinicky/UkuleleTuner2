package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent

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
