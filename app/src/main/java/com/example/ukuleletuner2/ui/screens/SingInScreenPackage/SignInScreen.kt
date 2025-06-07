@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ukuleletuner2.ui.screens.SingInScreenPackage

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.ukuleletuner2.presentation.sign_in.SignInState
import com.example.ukuleletuner2.windowInfo.WindowOrientation
import com.example.ukuleletuner2.windowInfo.rememberWindowInfo

//https://www.youtube.com/watch?v=zCIfBbm06QM (full)
//https://www.youtube.com/watch?v=HmXgVBys7BU (screen rotation)

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

    when (windowInfo.screenOrientation) {
        is WindowOrientation.Portrait -> {
            PortraitSignInLayout(onSignInClick)
        }

        is WindowOrientation.Landscape -> {
            LandscapeSignInLayout(onSignInClick)
        }
    }
}





