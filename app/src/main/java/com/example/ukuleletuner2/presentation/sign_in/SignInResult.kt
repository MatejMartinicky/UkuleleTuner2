package com.example.ukuleletuner2.presentation.sign_in

//https://www.youtube.com/watch?v=zCIfBbm06QM (full)

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
     val profilePictureUrl: String?
)
