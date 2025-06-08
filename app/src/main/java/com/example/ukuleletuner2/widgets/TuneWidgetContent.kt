/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import com.example.ukuleletuner2.R

/**
 * main content layout for ukulele tuner widget
 *
 * displays play/stop button, frequency information, ukulele image with string indicators
 *
 * @param viewModel widget viewmodel managing tuning state
 */
@Composable
fun TuneWidgetContent(viewModel: TuningWidgetViewModel) {
    val context = LocalContext.current
    val strings = UkuleleString.getAllStrings()
    val tuningStatus by viewModel.tuningStatus.collectAsState()
    val isListening by viewModel.isListening.collectAsState()
    val frequency by viewModel.frequency.collectAsState()

    val imageHeight = 200.dp
    val stringSpacing = 8.dp
    val buttonSize = 60.dp
    val stringThickness = 3.dp

    LaunchedEffect(viewModel) {
        WidgetManager.setViewModel(viewModel)
    }

    Column(
        modifier = GlanceModifier.fillMaxSize().background(Color.DarkGray).padding(8.dp),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
    ) {
        PlayButton(
            buttonSize,
            isListening = isListening,
            context = context,
            onStart = actionRunCallback<StartTuningAction>(),
            onStop = actionRunCallback<StopTuningAction>()
        )

        Text(
            text = when {
                frequency > 0 -> context.getString(
                    R.string.widget_frequency,
                    frequency.toInt().toString()
                )

                isListening -> context.getString(R.string.widget_stop)
                else -> context.getString(R.string.widget_start)
            },
            modifier = GlanceModifier.padding(8.dp)
        )

        Box(
            contentAlignment = Alignment.Center
        ) {

            Image(
                provider = ImageProvider(R.drawable.ukulele_nack),
                contentDescription = stringResource(R.string.widget_image_description),
                modifier = GlanceModifier
                    .size(imageHeight),
                contentScale = ContentScale.Fit
            )

            Row(
                verticalAlignment = Alignment.Vertical.CenterVertically,
                modifier = GlanceModifier.padding(16.dp)
            ) {
                repeat(4) { i ->
                    StringIndicator(
                        color = strings[i].color,
                        inTune = tuningStatus[strings[i]] == true,
                        size = imageHeight,
                        stringThickness = stringThickness,
                        modifier = GlanceModifier.padding(horizontal = stringSpacing)
                    )
                }
            }
        }
    }
}