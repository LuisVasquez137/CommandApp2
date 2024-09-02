package com.aplicacion.commandapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aplicacion.commandapp.presentation.screens.login.LoginScreen
import com.aplicacion.commandapp.presentation.screens.profile.ProfileScreen
import com.aplicacion.commandapp.presentation.screens.signup.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.LoginScreen.route
    ) {
        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(AppScreen.ProfileScreen.route){
            ProfileScreen(navController)
        }
    }
}