/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Yanneck Reiß (YouTube) -
 * "Learn How To Implement The Android Jetpack Compose Lazy Row Centered Snap Fling Behavior"
 *      https://www.youtube.com/watch?v=6BVPFyms2iE
 *(but not much [that snapping is not implemented])
 */
package com.example.ukuleletuner2.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.images.FlagImage
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel
/**
 * data class representing a language option in the selector
 *
 * @param code language code (e.g., "en", "sk", "de", "es")
 * @param flagResource Drawable resource ID for that country flag
 */
data class Language(
    val code: String,
    val flagResource: Int
)
/**
 * Language selection component that displays available languages as clickable flag icons
 *
 * @param settingsViewModel ViewModel containing available languages and language change logic
 */
@Composable
fun LanguageSelector(settingsViewModel: SettingsViewModel) {
    val context = LocalContext.current

    Column {
        Text(stringResource(R.string.settings_language))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(settingsViewModel.languages) { language ->
                FlagImage(
                    flagPainter = painterResource(id = language.flagResource),
                    size = 60.dp,
                    onClick = {
                        settingsViewModel.changeLanguageAndRestart(context, language.code)
                    }
                )
            }
        }
    }
}