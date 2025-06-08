/**
 * @author Matej Martinicky but lot from tutorial
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Create a Navigation Drawer With Jetpack Compose - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=JLICaBEiJS0
 * @see source: Philipp Lackner (YouTube) -
 * "How to Support All Screen Sizes in Jetpack Compose"
 *       https://www.youtube.com/watch?v=HmXgVBys7BU
 * (screen rotation)
 */
package com.example.ukuleletuner2.ui.screens.TunerScreenPackage

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.audioplayer.AndroidAudioPlayer
import com.example.ukuleletuner2.viewModels.TunerViewModel.TunerViewModel
import com.example.ukuleletuner2.viewModels.TunerViewModel.TuningStatus
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

/**
 * tuner screen that adapts layout based on orientation and handles audio processing
 *
 * @param onNavigateToSettings callback to navigate to settings screen
 * @param onNavigateToChords callback to navigate to chords screen
 */
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

    val player = remember { AndroidAudioPlayer(context) }
    var playingStringId by remember { mutableIntStateOf(-1) }

    val displayStatus = when (tuneStatus.status) {
        TuningStatus.ERROR -> context.getString(R.string.tuning_error)
        TuningStatus.WAITING -> context.getString(R.string.tuning_waiting)
        TuningStatus.NOT_TUNED -> context.getString(R.string.tuning_waiting)
        TuningStatus.TUNED -> context.getString(R.string.tuning_in_tune, tuneStatus.note ?: "")
        else -> {}
    }

    val strings = remember {
        listOf(
            UkuleleString(1, R.string.C_button, R.raw.ukulele_string_c),
            UkuleleString(2, R.string.G_button, R.raw.ukulele_string_g),
            UkuleleString(3, R.string.E_button, R.raw.ukulele_string_e),
            UkuleleString(4, R.string.A_button, R.raw.ukulele_string_a),
        )
    }

    DisposableEffect(player) {
        player.setAfterHook {
            playingStringId = -1
        }
        onDispose {
            player.stop()
        }
    }

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitTunerLayout(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = onNavigateToChords,
                viewModel = viewModel,
                isRecording = isRecording,
                frequency = frequency,
                displayStatus = displayStatus as String,
                strings = strings,
                playingStringId = playingStringId,
                onStringPlayed = { string ->
                    playingStringId = string.id
                    try {
                        player.playResource(string.audioFileName)
                    } catch (e: Exception) {
                        playingStringId = -1
                    }
                }
            )
        }

        is WindowOrientation.Landscape -> {
            LandscapeTunerLayout(
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToChords = onNavigateToChords,
                viewModel = viewModel,
                isRecording = isRecording,
                frequency = frequency,
                displayStatus = displayStatus as String,
                strings = strings,
                playingStringId = playingStringId,
                onStringPlayed = { string ->
                    playingStringId = string.id
                    try {
                        player.playResource(string.audioFileName)
                    } catch (e: Exception) {
                        playingStringId = -1
                    }
                }
            )
        }
    }
}
