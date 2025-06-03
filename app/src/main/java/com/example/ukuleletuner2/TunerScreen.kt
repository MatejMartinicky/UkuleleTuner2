package com.example.ukuleletuner2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ukuleletuner2.customLayouts.InstrumentLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ukuleletuner2.viewModels.TunerViewModel.TunerViewModel
import com.example.ukuleletuner2.viewModels.TunerViewModel.TuningStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit
) {
    val context = LocalContext.current
    val viewModel: TunerViewModel = viewModel { TunerViewModel(context) }
    val isRecording by viewModel.isRecording.collectAsState()
    val frequency by viewModel.frequency.collectAsState()
    val tuneStatus by viewModel.tuneStatus.collectAsState()

    val displayStatus = when (tuneStatus.status) {
        TuningStatus.ERROR -> context.getString(R.string.tuning_error)
        TuningStatus.WAITING -> context.getString(R.string.tuning_waiting)
        TuningStatus.NOT_TUNED -> context.getString(R.string.tuning_waiting)
        TuningStatus.TUNED -> context.getString(R.string.tuning_in_tune, tuneStatus.note ?: "")
        else -> {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.tuner_screen_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    Text(
                        stringResource(R.string.chords_navigation_text),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { onNavigateToChords() }.padding(16.dp)
                    )
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                },navigationIcon = {
                    IconButton(onClick = { /*Todo*/ }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = stringResource(R.string.menu_content_description),
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)
        ) {
            val (freq, tune, btn, inst) = createRefs()

            Text(
                stringResource(R.string.detected_frequency, "%.2f".format(frequency)),
                modifier = Modifier.constrainAs(freq) {
                    top.linkTo(parent.top, 32.dp)
                    centerHorizontallyTo(parent)
                }
            )

            Text(
                stringResource(R.string.tuning_status, displayStatus),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.constrainAs(tune) {
                    top.linkTo(freq.bottom, 20.dp)
                    centerHorizontallyTo(parent)
                }
            )

            Button(
                onClick = { viewModel.toggle() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isRecording) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.constrainAs(btn) {
                    top.linkTo(tune.bottom, 16.dp)
                    centerHorizontallyTo(parent)
                }
            ) {
                Text(if (isRecording) stringResource(R.string.stop_tuning) else stringResource(R.string.start_tuning))
            }

            InstrumentLayout(
                modifier = Modifier.constrainAs(inst) {
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(parent)
                }
            )
        }
    }
}