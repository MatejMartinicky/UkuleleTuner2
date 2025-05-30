package com.example.ukuleletuner2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel

//new way of navigation not so good
//https://www.youtube.com/watch?v=AIC_OFQ1r3k
//last one good but not all of it needed
// https://developer.android.com/guide/navigation/design/activity-destinations
//maybe next
//https://www.youtube.com/watch?v=FIEnIBq7Ups
@Composable
fun Navigation(themeViewModel: ThemeViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WelcomeScreen
    ) {
        composable<WelcomeScreen> { WelcomeScreen( onNavigateToTunerScreen = { navController.navigate(route = InstrumentChoiceScreen) } ) }

        //todo route here has to be absolutely changed!!!
        composable<InstrumentChoiceScreen> { InstrumentChoiceScreen( onNavigateToTunerScreen = { navController.navigate(route = TunerScreen) } ) }
        //TODO
        composable<TunerScreen> { TunerScreen(onNavigateToSettings = {navController.navigate(route = SettingsScreen) },
                                                onNavigateToChords = {navController.navigate(route = ChordsScreen) }) } //change when settings done


        composable<ChordsScreen> { ChordsScreen() } //change when settings done


        composable<SettingsScreen> { SettingsScreen(themeViewModel) } //these has to be here how I understand it is like this
        //composable<SettingsScreen> matches it as path where SettingsScreen is path defined in screen @Serializable
        //object SettingsScreen and this matches it to settings composable SettingsScreen() they don't even have to have same name



    }
}
