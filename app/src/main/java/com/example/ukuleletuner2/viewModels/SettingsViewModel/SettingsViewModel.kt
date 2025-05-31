package com.example.ukuleletuner2.viewModels.SettingsViewModel

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.cards.Language
import com.example.ukuleletuner2.utility.updateLocale


class SettingsViewModel : ViewModel() {
    var language = mutableStateOf("en")
        private set

    val languages = listOf(
        Language("en", R.drawable.flag_usa),
        Language("sk", R.drawable.flag_slovak),
        Language("de", R.drawable.flag_german),
        Language("es", R.drawable.flag_spanish)
    )

    fun setLanguage(lang: String) {
        language.value = lang
    }

    fun changeLanguageAndRestart(context: Context, languageCode: String) {
        updateLocale(context, languageCode)
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