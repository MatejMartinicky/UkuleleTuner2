package com.example.ukuleletuner2

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ukuleletuner2.presentation.sign_in.GoogleAuthUiClient
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel
import com.example.ukuleletuner2.ui.theme.AppTheme
import com.example.ukuleletuner2.ui.theme.ColorThemes
import com.google.android.gms.auth.api.identity.Identity

//comenting on behavior
class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mic permission
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )

        setContent {
            val themeViewModel: ThemeViewModel = viewModel {
                ThemeViewModel(initialTheme = ColorThemes.White)
            }

            AppTheme(themeViewModel = themeViewModel) {
                Surface(tonalElevation = 5.dp) {
                    Navigation(
                        themeViewModel = themeViewModel,
                        googleAuthUiClient = googleAuthUiClient
                    )
                }
            }
        }
    }
}