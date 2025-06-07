package com.example.ukuleletuner2.ui.screens.InstrumentChoiceScreenPackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
internal fun PortraitInstrumentLayout(onNavigateToTunerScreen: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            InstrumentChoiceTitle()

            Spacer(modifier = Modifier.height(48.dp))

            InstrumentSelector(onNavigateToTunerScreen)

            Spacer(modifier = Modifier.height(48.dp))

        }
    }
}