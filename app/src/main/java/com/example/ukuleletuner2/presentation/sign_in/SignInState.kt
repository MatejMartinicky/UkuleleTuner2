/**
 * @author from referenced tutorial
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "Firebase Google Sign-In With Jetpack Compose & Clean Architecture - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=zCIfBbm06QM
 */

package com.example.ukuleletuner2.presentation.sign_in

/**
 * State for Google sign in process
 *
 * @param isSignInSuccessful true if sign  in successful false otherwise
 * @param signInError nullable error message to show
 */
data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
