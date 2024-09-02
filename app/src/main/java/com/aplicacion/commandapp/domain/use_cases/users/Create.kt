package com.aplicacion.commandapp.domain.use_cases.users

import com.aplicacion.commandapp.domain.model.User
import com.aplicacion.commandapp.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository)  {
    suspend operator fun invoke(user: User) = repository.createUser(user)

}