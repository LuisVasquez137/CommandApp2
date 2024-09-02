package com.aplicacion.commandapp.domain.repository

import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRespository {

    val currentUser: FirebaseUser?
  suspend fun login(email: String, password: String): Response<FirebaseUser>

  suspend fun register(user: User): Response<FirebaseUser>
    fun logout()

}