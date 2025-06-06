package com.example.ukuleletuner2.ui.components.cards

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.ui.components.images.FlagImage
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel

data class Language(
    val code: String,
    val flagResource: Int
)

//https://www.youtube.com/watch?v=6BVPFyms2iE (dor snapping in future )

//https://www.youtube.com/watch?v=6BVPFyms2iE

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