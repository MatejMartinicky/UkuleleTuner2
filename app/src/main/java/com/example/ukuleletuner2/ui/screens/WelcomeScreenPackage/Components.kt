package com.example.ukuleletuner2.ui.screens.WelcomeScreenPackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ukuleletuner2.R

@Composable
internal fun WelcomeImageWithGradient() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        WelcomeImage(modifier = Modifier.fillMaxSize())

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.95f to MaterialTheme.colorScheme.primaryContainer,
                            1.0f to MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
        )
    }
}

@Composable
internal fun WelcomeImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.man_plaing_ukulele),
        contentDescription = stringResource(R.string.welcome_image_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
internal fun WelcomeButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .size(77.dp)
            .clip(CircleShape)
    ) {
        Text(
            text = stringResource(R.string.next_arrow),
            fontSize = 34.sp
        )
    }
}