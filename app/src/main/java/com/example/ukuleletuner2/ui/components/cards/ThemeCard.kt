package com.example.ukuleletuner2.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ukuleletuner2.themeViewModel.ThemeViewModel
import com.example.ukuleletuner2.ui.theme.ColorThemes

@Composable
fun ThemeCard(themeViewModel: ThemeViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Theme Color",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = when (themeViewModel.currentTheme) {
                    ColorThemes.White -> "White"
                    ColorThemes.Black -> "Black"
                    ColorThemes.Green -> "Green"
                },
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Thin
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ColorThemes.entries.forEach { theme ->
                DropdownMenuItem(
                    text = {
                        Text(
                            when (theme) {
                                ColorThemes.White -> "White"
                                ColorThemes.Black -> "Black"
                                ColorThemes.Green -> "Green"
                            }
                        )
                    },
                    onClick = {
                        themeViewModel.updateTheme(theme)
                        expanded = false
                    }
                )
            }
        }
    }
}