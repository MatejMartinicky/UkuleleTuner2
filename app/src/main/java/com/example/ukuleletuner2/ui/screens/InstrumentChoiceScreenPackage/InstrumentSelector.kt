/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.InstrumentChoiceScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.buttons.InstrumentButton

/**
 * horizontal scrollable row of instrument selection buttons
 *
 * @param onNavigateToTunerScreen callback function for navigating to tuner screen after instrument selection
 */
@Composable
internal fun InstrumentSelector(onNavigateToTunerScreen: () -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            InstrumentButton(
                onNavigateToTunerScreen,
                painterResource(id = R.drawable.ukulele)
            )
        }
    }
}