package com.example.ukuleletuner2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)

@Composable
fun WelcomeScreen(
    onNavigateToLoginScreen: () -> Unit
) {
    val windowInfo = rememberWindowInfo()

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitWelcomeLayout(onNavigateToLoginScreen)
        }
        is WindowOrientation.Landscape -> {
            LandscapeWelcomeLayout(onNavigateToLoginScreen)
        }
    }
}

@Composable
private fun PortraitWelcomeLayout(onNavigateToLoginScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        WelcomeImageWithGradient()
        WelcomeContent(
            onNavigateToLoginScreen = onNavigateToLoginScreen,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun LandscapeWelcomeLayout(onNavigateToLoginScreen: () -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        WelcomeImage(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )

        WelcomeContent(
            onNavigateToLoginScreen = onNavigateToLoginScreen,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@Composable
private fun WelcomeImageWithGradient() {
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
private fun WelcomeImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.man_plaing_ukulele),
        contentDescription = stringResource(R.string.welcome_image_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
private fun WelcomeContent(
    onNavigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = TextStyle(
                fontSize = 54.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        WelcomeButton(onClick = onNavigateToLoginScreen)
    }
}

@Composable
private fun WelcomeButton(onClick: () -> Unit) {
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