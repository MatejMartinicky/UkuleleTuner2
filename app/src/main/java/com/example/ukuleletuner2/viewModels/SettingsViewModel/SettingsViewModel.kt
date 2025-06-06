package com.example.ukuleletuner2.viewModels.SettingsViewModel

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.cards.Language
import com.example.ukuleletuner2.utility.LocaleHelper


class SettingsViewModel : ViewModel() {
    var language = mutableStateOf("en") //to enum
        private set

    val languages = listOf(
        Language("en", R.drawable.flag_usa),
        Language("sk", R.drawable.flag_slovak),
        Language("de", R.drawable.flag_german),
        Language("es", R.drawable.flag_spanish)
    )

    fun initializeLanguage(context: Context) {
        val currentLang = LocaleHelper.getLanguage(context)
        language.value = currentLang
    }

    fun setLanguage(lang: String) {
        language.value = lang
    }

    fun changeLanguageAndRestart(context: Context, languageCode: String) {
        LocaleHelper.updateFirebaseTopics(context, languageCode)

        LocaleHelper.setLocale(context, languageCode)

        setLanguage(languageCode)

        val activity = context as Activity
        activity.recreate()
    }

    fun onMenuClick() {
        //TODO
    }

    fun onBackClick() {
        //TODO
    }
}