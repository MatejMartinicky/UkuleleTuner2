package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.ukuleletuner2.R
enum class UkuleleString(
    val displayNameRes: Int,
    val frequency: Double,
    val color: Color
) {
    G4(R.string.g4, 392.00, Color(0xFF59AB86)),
    C4(R.string.c4, 261.63, Color(0xFF659495)),
    E4(R.string.e4, 329.63, Color(0xFFF09222)),
    A4(R.string.a4, 440.00, Color(0xFFDD2625));

    fun getDisplayName(context: Context): String {
        return context.getString(displayNameRes)
    }

    companion object {
        fun getAllStrings() = values().toList()
    }
}