/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Create a Navigation Drawer With Jetpack Compose - Android Studio Tutorial"
 *     https://www.youtube.com/watch?v=bhrN7yFG0D4
 */
package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import com.example.ukuleletuner2.recorder.TuningRecorder

/**
 * main widget implementation for ukulele tuner
 *
 * provides tuning functionality directly from home screen widget
 */
object TuneWidget : GlanceAppWidget() {
    /**
     * creates widget content with tuning recorder and viewmodel
     *
     * @param context android context
     * @param id widget instance identifier
     */
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val tuningRecorder = TuningRecorder(context)
        val viewModel = TuningWidgetViewModel(tuningRecorder)

        provideContent {
            TuneWidgetContent(viewModel)
        }
    }
}

/**
 * widget receiver for handling widget lifecycle events
 */
class TuneWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TuneWidget
}
