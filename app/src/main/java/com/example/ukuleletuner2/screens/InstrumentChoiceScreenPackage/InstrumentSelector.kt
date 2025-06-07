package com.example.ukuleletuner2.screens.InstrumentChoiceScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.buttons.InstrumentButton


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