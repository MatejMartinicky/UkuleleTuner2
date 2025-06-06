package com.example.ukuleletuner2.presentation.sign_in

//https://www.youtube.com/watch?v=zCIfBbm06QM (full)

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
