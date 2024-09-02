package com.aplicacion.commandapp.domain.use_cases.auth

data class AuthUseCases (
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val register: Register,
    val logout: LogOut
)

