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
 * data class for sign in operation
 *
 * @param data nullable user data
 * @param errorMessage nullable error message
 */
data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

/**
 * data class that encapsulates user information
 *
 * @param userId user firebase ID
 * @param username nullable name of the user
 * @property nullable URL to profile picture of the user
 */
data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)
