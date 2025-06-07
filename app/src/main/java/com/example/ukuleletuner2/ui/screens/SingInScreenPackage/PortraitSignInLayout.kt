package com.example.ukuleletuner2.ui.screens.SingInScreenPackage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R

@Composable
internal fun PortraitSignInLayout(onSignInClick: () -> Unit) {
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