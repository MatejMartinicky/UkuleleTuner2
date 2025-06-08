/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.viewModels.themeViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.theme.ColorThemes

/**
 * viewModel for managing app color themes and theme switching
 */
class ThemeViewModel(initialTheme: ColorThemes = ColorThemes.Green) : ViewModel() {

    var currentTheme by mutableStateOf(ColorThemes.Green)
        private set

    val themes = ColorThemes.entries

    /**
     * updates the current theme
     *
     * @param theme color theme to apply
     */
    fun updateTheme(theme: ColorThemes) {
        currentTheme = theme
    }
    /**
     * gets the drawable resource for theme preview image
     *
     * @param theme color theme to get image for
     * @return drawable resource id for theme preview
     */
    fun getThemeImageResource(theme: ColorThemes): Int {
        return when (theme) {
            ColorThemes.White -> R.drawable.theme_white
            ColorThemes.Orange -> R.drawable.theme_orange
            ColorThemes.Green -> R.drawable.theme_green
            ColorThemes.Black -> R.drawable.theme_black
        }
    }
}