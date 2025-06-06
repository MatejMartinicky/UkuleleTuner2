package com.example.ukuleletuner2

import com.example.ukuleletuner2.R
import android.R.attr.onClick
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.CacheDrawModifierNode
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource


@Composable
fun WelcomeScreen(
    onNavigateToTunerScreen: () -> Unit,
    onNavigateToLoginScreen: () -> Unit
) {
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column{

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.man_plaing_ukulele),
                    contentDescription = "image of a cool man playing ukulele",
                    contentScale = ContentScale.Fit
                )
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
                {
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = stringResource(R.string.welcome),
                    style = TextStyle(
                        fontSize = 54.sp,
                        fontWeight = FontWeight.Black
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onNavigateToLoginScreen,
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
        }
    }
}