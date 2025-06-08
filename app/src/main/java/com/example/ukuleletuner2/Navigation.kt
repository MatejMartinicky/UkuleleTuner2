/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Phillipp Lackner (YouTube) -
 * "Type-Safe Navigation with the OFFICIAL Compose Navigation Library"
 *      https://www.youtube.com/watch?v=tSlE-OfCV40
 * @see source: GoogleTeam (DeveloperAndroid) -
 * "Type-Safe Navigation with the OFFICIAL Compose Navigation Library"
 *      https://developer.android.com/guide/navigation/design/activity-destinations
 * @see source: Phillipp Lackner (YouTube) -
 * "Full Guide to Nested Navigation Graphs in Jetpack Compose"
 *      https://www.youtube.com/watch?v=FIEnIBq7Ups
 * @see source: Phillipp Lackner (YouTube) -
 * "Firebase Google Sign-In With Jetpack Compose & Clean Architecture - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=zCIfBbm06QM
 */
package com.example.ukuleletuner2

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ukuleletuner2.presentation.sign_in.GoogleAuthUiClient
import com.example.ukuleletuner2.presentation.sign_in.SignInViewModel
import com.example.ukuleletuner2.ui.screens.ChordsScreenPackage.ChordsScreen
import com.example.ukuleletuner2.ui.screens.InstrumentChoiceScreenPackage.InstrumentChoiceScreen
import com.example.ukuleletuner2.ui.screens.SettingsScreenPackage.SettingsScreen
import com.example.ukuleletuner2.ui.screens.SingInScreenPackage.SignInScreen
import com.example.ukuleletuner2.ui.screens.TunerScreenPackage.TunerScreen
import com.example.ukuleletuner2.ui.screens.WelcomeScreenPackage.WelcomeScreen
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel
import kotlinx.coroutines.launch

/**
 * Main navigation composable that defines the app's navigation graph
 * This function sets up all the routes and navigation logic for the ukulele tuner app
 *
 * Type-safe navigation: composable<SettingsScreen> matches the @Serializable object SettingsScreen
 * as the route path, which then renders the SettingsScreen() composable function.
 * The route object and composable function don't need to have the same name.
 *
 * @param themeViewModel ViewModel for managing app theme settings
 * @param googleAuthUiClient Client for handling Google authentication
 */
@Composable
fun Navigation(
    themeViewModel: ThemeViewModel,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WelcomeScreen
    ) {
        composable<WelcomeScreen> {
            WelcomeScreen(
                onNavigateToLoginScreen = {
                    navController.navigate(route = SignInScreen)
                }
            )
        }

        composable<InstrumentChoiceScreen> {
            InstrumentChoiceScreen(
                onNavigateToTunerScreen = {
                    navController.navigate(route = TunerScreen)
                }
            )
        }

        composable<TunerScreen> {
            TunerScreen(
                onNavigateToSettings = {
                    navController.navigate(route = SettingsScreen)
                },
                onNavigateToChords = {
                    navController.navigate(route = ChordsScreen)
                }
            )
        }

        composable<ChordsScreen> {
            ChordsScreen(
                onNavigateToSettings = {
                    navController.navigate(route = SettingsScreen)
                },
                onNavigateToTunerScreen = {
                    navController.navigate(route = TunerScreen)
                }
            )
        }

        composable<SettingsScreen> {
            val settingsViewModel: SettingsViewModel = viewModel()
            SettingsScreen(
                onNavigateToTuner = {
                    navController.navigate(route = TunerScreen)
                },
                onNavigateToChords = {
                    navController.navigate(route = ChordsScreen)
                },
                themeViewModel = themeViewModel,
                settingsViewModel = settingsViewModel
            )
        }

        composable<SignInScreen> {
            val viewModel: SignInViewModel = viewModel()
            val state by viewModel.state.collectAsState()
            val scope = rememberCoroutineScope()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    navController.navigate(InstrumentChoiceScreen)
                    viewModel.resetState()
                }
            }

            SignInScreen(
                state = state,
                onSignInClick = {
                    scope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }
    }
}
