package com.example.ukuleletuner2

import kotlinx.serialization.Serializable

/**
 * type-safe navigation routes for the ukulele tuner app
 *
 * these @Serializable objects define the navigation destinations for Compose Navigation

 * each object represents a unique route/screen in the app's navigation graph
 *
 * the @Serializable annotation enables type-safe navigation by allowing these objects
 * to be used as route parameters in navController.navigate() calls.
 */
@Serializable
object WelcomeScreen

@Serializable
object TunerScreen

@Serializable
object SettingsScreen

@Serializable
object InstrumentChoiceScreen

@Serializable
object ChordsScreen

@Serializable
object SignInScreen