/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.viewModels.SettingsViewModel

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.cards.Language
import com.example.ukuleletuner2.utility.LocaleHelper
 /**
 * viewModel for managing app settings, primarily language selection and switching
 */
class SettingsViewModel : ViewModel() {
    var language = mutableStateOf("en")
        private set

    val languages = listOf(
        Language("en", R.drawable.flag_usa),
        Language("sk", R.drawable.flag_slovak),
        Language("de", R.drawable.flag_german),
        Language("es", R.drawable.flag_spanish)
    )
     /**
      * load the user language and update UI
      *
      * @param context android context for accessing SharedPreferences
      */
    fun initializeLanguage(context: Context) {
        val currentLang = LocaleHelper.getLanguage(context)
        language.value = currentLang
    }

     /**
      * updates the language state for UI
      *
      * this only changes the ViewModel state
      * use changeLanguageAndRestart() for full language switching
      *
      * @param lang language code to set as selected
      */
    fun setLanguage(lang: String) {
        language.value = lang
    }
     /**
      * performs complete language change
      *
      * -updates firebase topic subscriptions for in-language notifications
      * -changes system language through LocaleHelper
      * -updates ViewModel
      * -restart the activity to apply changes throughout the UI
      *
      * @param context context
      * @param languageCode language code to switch to
      */
    fun changeLanguageAndRestart(context: Context, languageCode: String) {

        LocaleHelper.updateFirebaseTopics(context, languageCode)
        LocaleHelper.setLocale(context, languageCode)
        setLanguage(languageCode)

        val activity = context as Activity
        activity.recreate()
    }

     /**
      * handles menu button interaction in settings screen
      */
    fun onMenuClick() {
        //TODO
    }

     /**
      * handles back navigation from settings screen
      */
    fun onBackClick() {
        //TODO
    }
}