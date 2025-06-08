/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
/**
 * circular instrument selection button with image icon
 *
 * @param onClick callback function triggered when the button is pressed
 * @param painter image painter for the instrument icon displayed in the button
 */
@Composable
fun InstrumentButton(
    onClick: () -> Unit,
    painter: Painter
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.instrument_button_content_description),
            contentScale = ContentScale.Fit
        )
    }
}