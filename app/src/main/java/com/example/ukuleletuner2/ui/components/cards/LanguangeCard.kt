package com.example.ukuleletuner2.ui.components.cards

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.utility.updateLocale
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel


@Composable
fun LanguageSelector(settingsViewModel: SettingsViewModel) {
    val context = LocalContext.current

    Column {
        Text(stringResource(R.string.settings_language))
        Row {
            Button(onClick = {
                settingsViewModel.changeLanguageAndRestart(context, "en") //maybe to enum
            }) {}

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                settingsViewModel.changeLanguageAndRestart(context, "sk")
            }) {}
        }
    }
}