package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ukuleletuner2.InstrumentChoiceTitle
import com.example.ukuleletuner2.ui.components.buttons.InstrumentButton
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

@Composable
fun InstrumentChoiceScreen(onNavigateToTunerScreen: () -> Unit) {
    val windowInfo = rememberWindowInfo()

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitInstrumentLayout(onNavigateToTunerScreen)
        }
        is WindowOrientation.Landscape -> {
            LandscapeInstrumentLayout(onNavigateToTunerScreen)
        }
    }
}

@Composable
private fun PortraitInstrumentLayout(onNavigateToTunerScreen: () -> Unit) {
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

@Composable
private fun LandscapeInstrumentLayout(onNavigateToTunerScreen: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            InstrumentChoiceTitle()
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            InstrumentSelector(onNavigateToTunerScreen)

            Spacer(modifier = Modifier.height(32.dp))

        }
    }
}

@Composable
private fun InstrumentChoiceTitle() {
    Text(
        text = stringResource(R.string.instrument_choice_welcome),
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun InstrumentSelector(onNavigateToTunerScreen: () -> Unit) {
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