/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Create a Navigation Drawer With Jetpack Compose - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=bhrN7yFG0D4
 */
package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback

/**
 * action callback for starting tuning from widget
 *
 * handles widget button press to begin tuning process
 */
class StartTuningAction : ActionCallback {
    /**
     * executes start tuning action when widget button is pressed
     *
     * @param context android context
     * @param glanceId widget instance identifier
     * @param parameters action parameters (unused)
     */
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        WidgetManager.startTuning()
    }
}
