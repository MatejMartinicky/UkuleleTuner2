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
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.utility.updateLocale
import com.example.ukuleletuner2.viewModels.SettingsViewModel.SettingsViewModel


@Composable
fun LanguageSelector(settingsViewModel: SettingsViewModel) {
    val context = LocalContext.current
    val activity = context as Activity

    Column {
        Text("Language")
        Row {
            Button(onClick = {
                updateLocale(context, "en")
                activity.recreate() //recreating activity not idela but Ireally dont have time
            }) {}

            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                updateLocale(context, "sk")
                activity.recreate()
            }) {}
        }
    }
}