/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: kenmaro's prototyping (YouTube) -
 * "[JetpackCompose] Firebase Cloud Messaging to Send Push Notification"
 *      https://www.youtube.com/watch?v=tSlE-OfCV40 (this for that notification adding)
 */
package com.example.ukuleletuner2

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ukuleletuner2.presentation.sign_in.GoogleAuthUiClient
import com.example.ukuleletuner2.ui.theme.AppTheme
import com.example.ukuleletuner2.ui.theme.ColorThemes
import com.example.ukuleletuner2.utility.LocaleHelper
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    /**
     * applies saved language locale when activity context is created
     *
     * @param newBase base context to attach locale to
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getLanguage(newBase)))
    }
    /**
     * initializes app with permissions, firebase topics, and UI setup
     *
     * @param savedInstanceState saved activity state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeFirebaseTopics()

        //NOTIFICATION work-------------------------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }

        //MIC work----------------------------
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

    /**
     * subscribes to firebase topics based on current language
     *
     * enables localized notifications for news and app updates
     */
    private fun initializeFirebaseTopics() {
        val currentLanguage = LocaleHelper.getLanguage(this)
        FirebaseMessaging.getInstance().subscribeToTopic(currentLanguage)
    }
}