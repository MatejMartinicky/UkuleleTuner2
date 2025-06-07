package com.example.ukuleletuner2.screens.InstrumentChoiceScreenPackage

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ukuleletuner2.R


@Composable
internal fun InstrumentChoiceTitle() {
    Text(
        text = stringResource(R.string.instrument_choice_welcome),
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        textAlign = TextAlign.Center
    )
}