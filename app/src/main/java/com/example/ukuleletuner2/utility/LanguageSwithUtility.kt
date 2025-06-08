/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.utility

import android.content.Context
import android.content.res.Configuration
import androidx.core.content.edit
import com.google.firebase.messaging.FirebaseMessaging
import java.util.Locale

/**
 * utility class for language/locale changes at runtime
 */
class LocaleHelper {
    /**
     * static object that belongs to this class not its instances
     */
    companion object {
        private const val SELECTED_LANGUAGE = "selected_language"
        private const val PREFS_NAME = "app_prefs"

        /**
        * changes the app's language and saves the preference.
        *
        * @param context actual android context
        * @param language language code ("en", "es", "sk")
        * @return new context with updated language configuration
        */
        fun setLocale(context: Context, language: String): Context {
            //save language choice to SharedPreferences
            persist(context, language)
            //apply
            return updateResources(context, language)
        }

        /**
         * get current context
         *
         * @param context get current context
         *
         * @return language code
         */
        fun getLanguage(context: Context): String {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            return prefs.getString(SELECTED_LANGUAGE, Locale.getDefault().language) ?: "en"
        }

        /**
        * saves language to SharedPreferences to remember it when closed
        *
        * @param context context to access SharedPreferences
        * @param language language code to save
        */
        private fun persist(context: Context, language: String) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit {
                putString(SELECTED_LANGUAGE, language)
            }
        }

        /**
         * updates the system language configuration
         *
         * @param context current context
         * @param language language code ("en", "es", "sk")
         * @return new context with updated language configuration
         */
        private fun updateResources(context: Context, language: String): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val configuration = Configuration(context.resources.configuration)
            configuration.setLocale(locale)

            return context.createConfigurationContext(configuration)
        }
        /**
         * updates Firebase topic subscriptions to have multilingual notifications
         *
         * Example topics: "en", "es", "sk" for English, Spanish, Slovak etc.
         *
         * @param context context for accessing current language
         * @param newLanguage language code for the new language
         */
        fun updateFirebaseTopics(context: Context, newLanguage: String) {
            val oldLanguage = getLanguage(context)

            if (oldLanguage != newLanguage) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(oldLanguage)

                FirebaseMessaging.getInstance().subscribeToTopic(newLanguage)
            }
        }
    }
}