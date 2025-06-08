/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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


/**
 * theme selection component that displays available color themes as clickable preview images
 *
 * @param themeViewModel ViewModel containing available themes and theme change logic
 */
@Composable
fun ThemeCard(themeViewModel: ThemeViewModel) {
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
            items(themeViewModel.themes) { theme ->
                ThemeImage(
                    themePainter = painterResource(id = themeViewModel.getThemeImageResource(theme)),
                    size = 60.dp,
                    onClick = {
                        themeViewModel.updateTheme(theme)
                    }
                )
            }
        }
    }
}
