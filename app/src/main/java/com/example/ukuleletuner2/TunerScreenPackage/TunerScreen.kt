package com.example.ukuleletuner2.TunerScreenPackage

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.viewModels.TunerViewModel.TunerViewModel
import com.example.ukuleletuner2.viewModels.TunerViewModel.TuningStatus
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)
//https://www.youtube.com/watch?v=JLICaBEiJS0 (navigation drawer)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit,
) {
    val windowInfo = rememberWindowInfo()
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

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitTunerLayout(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = onNavigateToChords,
                viewModel = viewModel,
                isRecording = isRecording,
                frequency = frequency,
                displayStatus = displayStatus as String
            )
        }

        is WindowOrientation.Landscape -> {
            LandscapeTunerLayout(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = onNavigateToChords,
                viewModel = viewModel,
                isRecording = isRecording,
                frequency = frequency,
                displayStatus = displayStatus as String
            )
        }
    }
}
