package com.example.ukuleletuner2.viewModels.SettingsViewModel

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ukuleletuner2.utility.updateLocale


class SettingsViewModel : ViewModel() {
    var language = mutableStateOf("en")
        private set

    fun setLanguage(lang: String) {
        language.value = lang
    }

    fun changeLanguageAndRestart(context: Context, languageCode: String) {
        updateLocale(context, languageCode)
        setLanguage(languageCode)

        val activity = context as Activity
        activity.recreate()
    }
}