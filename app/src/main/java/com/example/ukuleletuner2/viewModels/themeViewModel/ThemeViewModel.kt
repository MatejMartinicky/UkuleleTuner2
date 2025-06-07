package com.example.ukuleletuner2.viewModels.themeViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.theme.ColorThemes

class ThemeViewModel(initialTheme: ColorThemes = ColorThemes.Green) : ViewModel() {

    var currentTheme by mutableStateOf(ColorThemes.Green)
        private set

    fun updateTheme(theme: ColorThemes) {
        currentTheme = theme
    }

    val themes = ColorThemes.entries

    fun getThemeImageResource(theme: ColorThemes): Int {
        return when (theme) {
            ColorThemes.White -> R.drawable.theme_white
            ColorThemes.Orange -> R.drawable.theme_orange
            ColorThemes.Green -> R.drawable.theme_green
            ColorThemes.Black -> R.drawable.theme_black
        }
    }
}