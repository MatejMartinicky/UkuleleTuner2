/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.TunerScreenPackage

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ukuleletuner2.R
/**
 * button that toggles tuner recording state with visual feedback
 *
 * @param isRecording current recording state of the tuner
 * @param onToggle callback to toggle recording state
 * @param modifier optional modifier for styling and layout
 */
@Composable
internal fun TunerButton(
    isRecording: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onToggle,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isRecording) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        Text(if (isRecording) stringResource(R.string.stop_tuning) else stringResource(R.string.start_tuning))
    }
}