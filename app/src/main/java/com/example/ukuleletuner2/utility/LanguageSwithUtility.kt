package com.example.ukuleletuner2.utility

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import java.util.Locale
import androidx.core.content.edit

//source
//rename
//pull old source from git history and add that too
class LocaleHelper {
    companion object {
        private const val SELECTED_LANGUAGE = "selected_language"
        private const val PREFS_NAME = "app_prefs"

        fun setLocale(context: Context, language: String): Context {
            persist(context, language)
            return updateResources(context, language)
        }

        fun getLanguage(context: Context): String {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getString(SELECTED_LANGUAGE, Locale.getDefault().language) ?: "en"
        }

        private fun persist(context: Context, language: String) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit() { putString(SELECTED_LANGUAGE, language) }
        }

        private fun updateResources(context: Context, language: String): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val configuration = Configuration(context.resources.configuration)
            configuration.setLocale(locale)

            return context.createConfigurationContext(configuration)
        }

        fun updateFirebaseTopics(context: Context, newLanguage: String) {
            val oldLanguage = getLanguage(context)

            if (oldLanguage != newLanguage) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(oldLanguage)

                FirebaseMessaging.getInstance().subscribeToTopic(newLanguage)
            }
        }
    }
}