package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback

////https://www.youtube.com/watch?v=bhrN7yFG0D4
class StopTuningAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        WidgetManager.stopTuning()
    }
}