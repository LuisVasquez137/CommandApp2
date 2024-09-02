package com.aplicacion.commandapp.data.repository

import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.domain.model.User
import com.aplicacion.commandapp.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRespositoryImpl @Inject constructor(private val usersRef: CollectionReference) :
    UsersRepository {
    override suspend fun createUser(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }
}