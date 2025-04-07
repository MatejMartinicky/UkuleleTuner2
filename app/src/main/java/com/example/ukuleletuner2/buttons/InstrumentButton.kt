package com.example.ukuleletuner2.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

@Composable
fun InstrumentButton(onClick: () -> Unit, painter: Painter) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.White)
    ) {
        Image(
            painter = painter,
            contentDescription = "Instrument Button Image",
            contentScale = ContentScale.Fit
        )
    }
}