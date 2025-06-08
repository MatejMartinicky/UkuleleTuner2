/**
 * @author Matej Martinicky
 */
package com.example.ukuleletuner2.ui.screens.TunerScreenPackage

/**
 * Represents a single ukulele string with display and audio resources
 *
 * @param id unique identifier for the string
 * @param name string resource ID for the string name/note
 * @param audioFileName raw resource ID for the string's audio file
 */
data class UkuleleString(
    val id: Int,
    val name: Int,
    val audioFileName: Int
)