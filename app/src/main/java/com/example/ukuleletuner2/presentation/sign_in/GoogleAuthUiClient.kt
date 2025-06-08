/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "Firebase Google Sign-In With Jetpack Compose & Clean Architecture - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=zCIfBbm06QM
 */

package com.example.ukuleletuner2.presentation.sign_in

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.ukuleletuner2.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

/**
 * Authentication through Google Firebase Authentication
 *
 * Class authenticates user thorough firebase via Google One Tab and Firebase Authentication
 *
 * One Tab is feature that shows popup on bottom of a screen and user just clicks it
 * (he doest have to enter password login etc.)
 *
 * @param context context for accessing resources and services
 * @param oneTabClient Google OneTabClient for One tab authentication flow
 */
class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth

    /**
     * Starts Sing in process
     *
     * -Builds sign-in request with ID token provided
     * -calls on one tab client to start sing-in
     * -returns IntentSender for launching sign-in dialog
     *
     * Intent is like asking android to do something
     *
     * Intent: "Android open my login screen to do my authentication
     *      on my internal database of users" (if I had one)
     * IntentSender:
     *      "Google tells here you go take this intent sender,
     *      but you cant touch it it is mine, and give it to android when authentication is ready"
     *
     * @return IntentSender for launching sign-in UI, or null if initialization fails
     * @throws CancellationException if the operation is cancelled
     */
    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    /**
     * processes result from One Tab and creates firebase user session
     *
     * -gets user credentials for intent
     * -from that gets Google ID token for firebase
     * -creates credentials for google
     * -tries sign in into firebase using this credentials
     * -returns sign in result if success and throws if failed
     *
     * @param intent result for oneTab with user credentials
     * @return SignInResult  user data if success throws otherwise
     * @throws CancellationException if fails
     */
    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    /**
     * Signs out user from OneTab and Firebase
     *
     * @throws CancellationException if the sign outs unsuccessful
     */
    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is kotlinx.coroutines.CancellationException) throw e
        }
    }

    /**
     * Gets user from firebase database
     *
     * @return UserData if user is signed in, null if no user is signed in
     */
    fun getSignedInUser(): UserData? {
        return auth.currentUser?.run {
            UserData(
                userId = uid,
                username = displayName,
                profilePictureUrl = photoUrl?.toString()
            )
        }
    }

    /**
     * builds sign in request for OneTab
     *
     * Configuration options:
     * - setSupported(true): Enables Google ID token generation for Firebase auth
     * - setFilterByAuthorizedAccounts(false): Shows all Google accounts on device,
     *      not just previously authorized ones (gives users more choice)
     * - setServerClientId(): token that identifies this add to firebase
     *
     * Missing options (intentionally not used):
     * - setAutoSelectEnabled(): Not set, so users always see account selection
     *      (so it can by presented and shown it does something)
     */
    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .build()
    }
}