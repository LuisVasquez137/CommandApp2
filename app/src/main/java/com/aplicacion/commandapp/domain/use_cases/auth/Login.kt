package com.aplicacion.commandapp.domain.use_cases.auth

import com.aplicacion.commandapp.data.repository.AutnRepositoryImpl
import com.aplicacion.commandapp.domain.repository.AuthRespository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRespository) {


    suspend operator fun invoke (email: String, password: String) = repository.login(email, password)
}