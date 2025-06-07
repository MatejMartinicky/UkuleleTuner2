package com.example.ukuleletuner2.navigationDrawer

sealed class MenuItems {
    object Home : MenuItems()
    object Chords : MenuItems()
    object Settings : MenuItems()
}