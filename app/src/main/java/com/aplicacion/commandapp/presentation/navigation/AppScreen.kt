package com.aplicacion.commandapp.presentation.navigation

sealed class AppScreen (val route: String) {
    object LoginScreen : AppScreen("login_screen")
    object RegisterScreen : AppScreen("register_screen")
    object HomeScreen : AppScreen("home_screen")
    object ProfileScreen : AppScreen("profile_screen")
    object SettingsScreen : AppScreen("settings_screen")


}