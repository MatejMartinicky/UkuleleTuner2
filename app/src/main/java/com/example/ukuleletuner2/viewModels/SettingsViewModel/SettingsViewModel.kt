package com.example.ukuleletuner2.viewModels.SettingsViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class LanguageOptions(val id: String,
    val displayKey: String,
    val conde: String) {

}
class SettingsViewModel : ViewModel() {
    var language = mutableStateOf("en")
        private set

    fun setLanguage(lang: String) {
        language.value = lang
    }
}