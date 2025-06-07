package com.example.ukuleletuner2.navigationDrawer

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id: MenuItems,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)