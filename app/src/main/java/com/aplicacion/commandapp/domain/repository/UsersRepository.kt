package com.aplicacion.commandapp.domain.repository

import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.domain.model.User

interface UsersRepository {
    suspend fun createUser(user: User): Response<Boolean>
}