package com.example.ukuleletuner2.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.Box
import androidx.glance.layout.Spacer
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import com.example.ukuleletuner2.R

@Composable
fun TuneWidgetContent(viewModel: TuningWidgetViewModel) {
    val context = LocalContext.current
    val strings = UkuleleString.getAllStrings()
    val tuningStatus by viewModel.tuningStatus.collectAsState()
    val isListening by viewModel.isListening.collectAsState()
    val frequency by viewModel.frequency.collectAsState()

    LaunchedEffect(viewModel) {
        WidgetManager.setViewModel(viewModel)
    }

    Column(
        modifier = GlanceModifier.fillMaxSize().background(Color.DarkGray).padding(8.dp),
        verticalAlignment = Alignment.Vertical.CenterVertically,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
    ) {
        Row {
            Box(
                modifier = GlanceModifier
                    .cornerRadius(16.dp)
                    .background(if (isListening) Color.Gray else Color.Green)
                    .clickable(actionRunCallback<StartTuningAction>())
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(context.getString(R.string.widget_start))
            }

            Spacer(modifier = GlanceModifier.width(8.dp))

            Box(
                modifier = GlanceModifier
                    .cornerRadius(16.dp)
                    .background(if (!isListening) Color.Gray else Color.Red)
                    .clickable(actionRunCallback<StopTuningAction>())
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(context.getString(R.string.widget_stop))
            }
        }

        Text(
            text = when {
                frequency > 0 -> context.getString(R.string.widget_frequency, frequency.toInt().toString())
                isListening -> context.getString(R.string.widget_stop)
                else -> context.getString(R.string.widget_start)
            },
            modifier = GlanceModifier.padding(8.dp)
        )

        Row {
            repeat(2) { i ->
                TuneButton(
                    tone = strings[i].getDisplayName(context),
                    inTune = tuningStatus[strings[i]] ?: false,
                    buttonSize = 60,
                    buttonColor = strings[i].color,
                    modifier = GlanceModifier.padding(4.dp),
                    onClick = { }
                )
            }
        }

        Row {
            repeat(2) { i ->
                val index = i + 2
                TuneButton(
                    tone = strings[index].getDisplayName(context),
                    inTune = tuningStatus[strings[index]] ?: false,
                    buttonSize = 60,
                    buttonColor = strings[index].color,
                    modifier = GlanceModifier.padding(4.dp),
                    onClick = { }
                )
            }
        }
    }
}