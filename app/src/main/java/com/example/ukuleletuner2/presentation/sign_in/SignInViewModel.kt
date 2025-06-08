/**
 * @author from referenced tutorial
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "Firebase Google Sign-In With Jetpack Compose & Clean Architecture - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=zCIfBbm06QM
 */

package com.example.ukuleletuner2.presentation.sign_in

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for Sign in Screen
 *
 * Handles the sign-in state that the UI observes and uses StateFlow update UI.
 */
class SignInViewModel : ViewModel() {
    // private mutable state only this ViewModel can modify it
    private val _state = MutableStateFlow(SignInState())
    // public read-only state UI can observe but not modify
    val state = _state.asStateFlow()

    /**
     * Updates the sign-in state based on authentication result.
     *
     * @param result the result from Google sign-in operation
     */
    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    /**
    * Resets the sign-in state to initial values.
    */
    fun resetState() {
        _state.update { SignInState() }
    }
}