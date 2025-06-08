/**
 * @author Matej Martinicky
 */

package com.example.ukuleletuner2.navigationDrawer

/**
 * Sealed class that contains all menu items in NavigationDrawer
 *
 * Also could be Enum, but people in tutorial use sealed classes more often
 *  reasons:
 *      -more flexible
 *      -each item can be a different subclass
 * */
sealed class MenuItems {
    object Home : MenuItems()
    object Chords : MenuItems()
    object Settings : MenuItems()
}