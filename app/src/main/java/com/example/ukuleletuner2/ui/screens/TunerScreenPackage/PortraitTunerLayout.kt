/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.TunerScreenPackage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.customLayouts.InstrumentLayout
import com.example.ukuleletuner2.viewModels.TunerViewModel.TunerViewModel

/**
 * portrait tuner layout with vertical arrangement using ConstraintLayout
 *
 * @param onNavigateToSettings callback to navigate to settings screen
 * @param onNavigateToChords callback to navigate to chords screen
 * @param viewModel tuner view model for audio processing control
 * @param isRecording current recording state of the tuner
 * @param frequency detected audio frequency in Hz
 * @param displayStatus current tuning status message
 * @param strings list of ukulele strings with their properties
 * @param playingStringId ID of currently playing/selected string
 * @param onStringPlayed callback when a string is played/selected
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PortraitTunerLayout(
    onNavigateToSettings: () -> Unit,
    onNavigateToChords: () -> Unit,
    viewModel: TunerViewModel,
    isRecording: Boolean,
    frequency: Double,
    displayStatus: String,
    strings: List<UkuleleString>,
    playingStringId: Int,
    onStringPlayed: (UkuleleString) -> Unit
) {
    TunerWithDrawer(
        onNavigateToSettings = onNavigateToSettings,
        onNavigateToChords = onNavigateToChords
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
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

            TunerButton(
                isRecording = isRecording,
                onToggle = { viewModel.toggle() },
                modifier = Modifier.constrainAs(btn) {
                    top.linkTo(tune.bottom, 16.dp)
                    centerHorizontallyTo(parent)
                }
            )
            InstrumentLayout(
                modifier = Modifier.constrainAs(inst) {
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(parent)
                },
                strings = strings,
                playingStringId = playingStringId,
                onStringPlayed = onStringPlayed
            )

        }
    }
}