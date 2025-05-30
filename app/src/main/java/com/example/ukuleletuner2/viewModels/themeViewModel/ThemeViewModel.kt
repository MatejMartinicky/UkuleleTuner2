package com.example.ukuleletuner2.viewModels.themeViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ukuleletuner2.ui.theme.ColorThemes

class ThemeViewModel(initialTheme: ColorThemes = ColorThemes.Green) : ViewModel() {

    var currentTheme by mutableStateOf(ColorThemes.Green)
    private set

    fun updateTheme(theme: ColorThemes) {
        currentTheme = theme
    }
}