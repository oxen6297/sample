package com.example.sampletwo.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class Direction(val direction: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
}
