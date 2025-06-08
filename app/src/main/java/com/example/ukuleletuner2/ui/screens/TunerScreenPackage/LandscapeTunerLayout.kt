/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.TunerScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.customLayouts.InstrumentLayout
import com.example.ukuleletuner2.viewModels.TunerViewModel.TunerViewModel
/**
 * landscape tuner layout with side-by-side controls and instrument display
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
internal fun LandscapeTunerLayout(
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
        onNavigateToChords = onNavigateToChords,
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.detected_frequency, "%.2f".format(frequency)),
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Text(
                    stringResource(R.string.tuning_status, displayStatus),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TunerButton(
                    isRecording = isRecording,
                    onToggle = { viewModel.toggle() }
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                InstrumentLayout(
                    strings = strings,
                    playingStringId = playingStringId,
                    onStringPlayed = onStringPlayed
                )
            }
        }
    }
}