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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


@Composable
fun WelcomeScreen(onNavigateToTunerScreen: () -> Unit) {
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Color(0xFF7DE83A)
    ) {
        Box(modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.man_plaing_ukulele),
                contentDescription = "Cool Image",
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 5.dp)
                    .graphicsLayer {
                        shadowElevation = 1f
                        shape = androidx.compose.ui.graphics.RectangleShape
                        clip = true
                    }
                    .background(Color(0xFF7DE83A).copy(alpha = 0.4f)) //watch that youtube tutorial about adding images where it was
            )

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Text(
                text = "Welcome!",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Black
                )
            )

            Button(
                onClick = onNavigateToTunerScreen,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
                ) {
                Text(
                    color = Color(0xFF7DE83A),
                    text = "Lets start! ➡",
                    fontSize = 24.sp
                )
            }
        }


    }

}