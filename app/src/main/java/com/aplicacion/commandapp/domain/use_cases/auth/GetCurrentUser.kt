package com.aplicacion.commandapp.domain.use_cases.auth

import com.aplicacion.commandapp.domain.repository.AuthRespository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRespository) {

    operator fun invoke() = repository.currentUser
}