package com.example.ukuleletuner2.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.images.ThemeImage
import com.example.ukuleletuner2.viewModels.themeViewModel.ThemeViewModel
import com.example.ukuleletuner2.ui.theme.ColorThemes
import androidx.compose.foundation.lazy.items

fun getThemeImageResource(theme: ColorThemes): Int { //to view model
    return when (theme) {
        ColorThemes.White -> R.drawable.theme_white
        ColorThemes.Orange -> R.drawable.theme_orange
        ColorThemes.Green -> R.drawable.theme_green
        ColorThemes.Black -> R.drawable.theme_black
    }
}

@Composable
fun ThemeCard(themeViewModel: ThemeViewModel) {
    val themes = ColorThemes.entries

    Column {
        Text(
            text = stringResource(R.string.theme_color),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(themes) { theme ->
                ThemeImage(
                    themePainter = painterResource(id = getThemeImageResource(theme)),
                    size = 60.dp,
                    onClick = {
                        themeViewModel.updateTheme(theme)
                    }
                )
            }
        }
    }
}
