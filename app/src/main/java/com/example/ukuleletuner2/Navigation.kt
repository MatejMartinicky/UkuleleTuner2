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
import com.example.ukuleletuner2.presentation.sign_in.SignInScreen
import com.example.ukuleletuner2.presentation.sign_in.SignInViewModel
import com.example.ukuleletuner2.screens.ChordsScreenPackage.ChordsScreen
import com.example.ukuleletuner2.screens.InstrumentChoiceScreenPackage.InstrumentChoiceScreen
import com.example.ukuleletuner2.screens.SettingsScreenPackage.SettingsScreen
import com.example.ukuleletuner2.screens.TunerScreenPackage.TunerScreen
import com.example.ukuleletuner2.screens.WelcomeScreenPackage.WelcomeScreen
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel
import kotlinx.coroutines.launch

//new way of navigation not so good
//https://www.youtube.com/watch?v=AIC_OFQ1r3k
//last one good but not all of it needed
// https://developer.android.com/guide/navigation/design/activity-destinations
//maybe next
//https://www.youtube.com/watch?v=FIEnIBq7Ups
//for Sign in screen
//https://www.youtube.com/watch?v=zCIfBbm06QM
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

        //todo route here has to be absolutely changed!!!
        composable<InstrumentChoiceScreen> {
            InstrumentChoiceScreen(
                onNavigateToTunerScreen = {
                    navController.navigate(route = TunerScreen)
                }
            )
        }

        //TODO
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
        //these has to be here how I understand it is like this
        //composable<SettingsScreen> matches it as path where SettingsScreen is path defined in screen @Serializable
        //object SettingsScreen and this matches it to settings composable SettingsScreen() they don't even have to have same name

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
