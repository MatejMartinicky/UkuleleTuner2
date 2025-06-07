@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ukuleletuner2.presentation.sign_in

//https://www.youtube.com/watch?v=zCIfBbm06QM (full)
//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.ukuleletuner2.ui.components.buttons.GoogleLogoButton
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val windowInfo = rememberWindowInfo()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    when(windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitSignInLayout(onSignInClick)
        }
        is WindowOrientation.Landscape -> {
            LandscapeSignInLayout(onSignInClick)
        }
    }
}

@Composable
private fun PortraitSignInLayout(onSignInClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        SignInBackgroundImage(
            painter = painterResource(id = R.drawable.sing_in_image),
            modifier = Modifier.fillMaxSize()
        )

        SignInContent(
            logoSize = 200.dp,
            onSignInClick = onSignInClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
private fun LandscapeSignInLayout(onSignInClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        SignInBackgroundImage(
            painter = painterResource(id = R.drawable.sing_in_landscape),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )

        SignInContent(
            logoSize = 120.dp,
            onSignInClick = onSignInClick,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp)
        )
    }
}

@Composable
private fun SignInBackgroundImage(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
private fun SignInContent(
    logoSize: Dp,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        AppLogo(size = logoSize)

        Spacer(modifier = Modifier.weight(0.4f))

        SignInPrompt()

        Spacer(modifier = Modifier.weight(0.3f))

        SignInDisclaimer()

        Spacer(modifier = Modifier.weight(0.1f))

        SignInButton(onSignInClick)
    }
}

@Composable
private fun AppLogo(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.logo_high_resolution),
        contentDescription = null,
        modifier = Modifier.size(size)
    )
}

@Composable
private fun SignInPrompt() {
    Text(
        text = stringResource(R.string.sign_in_prompt),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun SignInDisclaimer() {
    Text(
        text = stringResource(R.string.sign_in_disclaimer),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun SignInButton(onSignInClick: () -> Unit) {
    GoogleLogoButton(
        text = stringResource(R.string.login_button),
        onClick = onSignInClick
    )
}