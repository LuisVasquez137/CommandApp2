package com.aplicacion.commandapp.domain.use_cases.auth

import com.aplicacion.commandapp.domain.model.User
import com.aplicacion.commandapp.domain.repository.AuthRespository
import javax.inject.Inject

class Register @Inject constructor(private val repository: AuthRespository) {

    suspend operator fun invoke(user: User) = repository.register(user)

}