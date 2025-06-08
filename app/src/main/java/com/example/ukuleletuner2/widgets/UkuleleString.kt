/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.widgets

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.ukuleletuner2.R
import com.example.ukuleletuner2.widgets.UkuleleString.values

/**
 * represents ukulele strings with their musical properties
 *
 * defines standard ukulele tuning (G-C-E-A) with frequencies and colors
 *
 * @param displayNameRes string resource for display name
 * @param frequency target frequency in Hz for proper tuning
 * @param color visual color for string identification
 */
enum class UkuleleString(
    val displayNameRes: Int,
    val frequency: Double,
    val color: Color
) {
    G4(R.string.g4, 392.00, Color(0xFF59AB86)),
    C4(R.string.c4, 261.63, Color(0xFF659495)),
    E4(R.string.e4, 329.63, Color(0xFFF09222)),
    A4(R.string.a4, 440.00, Color(0xFFDD2625));

    /**
     * gets localized display name for the string
     *
     * @param context android context for string resources
     * @return localized string name
     */
    fun getDisplayName(context: Context): String {
        return context.getString(displayNameRes)
    }
    /**
     * returns all ukulele strings as a list
     *
     * @return list of all four ukulele strings
     */
    companion object {
        fun getAllStrings() = values().toList()
    }
}