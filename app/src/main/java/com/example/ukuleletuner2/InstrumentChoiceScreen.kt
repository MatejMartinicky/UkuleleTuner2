package com.example.ukuleletuner2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ukuleletuner2.ui.components.buttons.InstrumentButton

@Composable
fun InstrumentChoiceScreen(onNavigateToTunerScreen: () -> Unit) {
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //TODO remove those spacers they are heere only that I can screen shot it and change to constrained layout

            Text(
                text = stringResource(R.string.instrument_choice_welcome),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(82.dp))

            Spacer(modifier = Modifier.height(16.dp))
            InstrumentButton(onNavigateToTunerScreen, painterResource(id = R.drawable.guitar))
            Spacer(modifier = Modifier.height(16.dp))
            InstrumentButton(onNavigateToTunerScreen, painterResource(id = R.drawable.ukulele))
            Spacer(modifier = Modifier.height(16.dp))
            InstrumentButton(onNavigateToTunerScreen, painterResource(id = R.drawable.banjo))

            Spacer(modifier = Modifier.height(82.dp))

            Button(
                onClick = onNavigateToTunerScreen,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(text = stringResource(R.string.instrument_choice_continue_button),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
