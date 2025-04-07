package com.example.ukuleletuner2

import android.R
import android.R.attr.id
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ukuleletuner2.buttons.InstrumentButton
import kotlin.contracts.contract

@Composable
fun InstrumentChoiceScreen(onNavigateToTunerScreen: () -> Unit) {
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color(0xFF66BB6A)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //TODO remove those spacers they are heere only that I can screen shot it and change to constrained layout

            Text(
                text = "What are you playing?",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(82.dp))

            Spacer(modifier = Modifier.height(16.dp))
            InstrumentButton(onNavigateToTunerScreen, painterResource(id = com.example.ukuleletuner2.R.drawable.guitar))
            Spacer(modifier = Modifier.height(16.dp))
            InstrumentButton(onNavigateToTunerScreen, painterResource(id = com.example.ukuleletuner2.R.drawable.ukulele))
            Spacer(modifier = Modifier.height(16.dp))
            InstrumentButton(onNavigateToTunerScreen, painterResource(id = com.example.ukuleletuner2.R.drawable.banjo))

            Spacer(modifier = Modifier.height(82.dp))

            Button(
                onClick = onNavigateToTunerScreen,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Text(text = "Continue",
                    color =  Color(0xFF66BB6A),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
