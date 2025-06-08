/**
 * @author Matej Martinicky
 *
 * References:
 * @see source: Philipp Lackner (YouTube) -
 * "How to Create a Navigation Drawer With Jetpack Compose - Android Studio Tutorial"
 *      https://www.youtube.com/watch?v=JLICaBEiJS0
 */
package com.example.ukuleletuner2.navigationDrawer

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class that is one Menu Item in NavigationDrawer
 *
 * @param id identifier for menu item
 * @param title title displayed to user (like Home, Chords, Settings)
 * @param contentDescription content descriptor for deaf or blind people when they click it it reads
 * @param icon icon that will be displayed in that navigation drawer
 *      but it needs to be added as vector and cant be svg
 *          has to ether downland it for https://fonts.google.com/icons
 *          and then add it via res/drawable/(new Vector Asset) chose from one there or add this
 */
data class MenuItem(
    val id: MenuItems,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)