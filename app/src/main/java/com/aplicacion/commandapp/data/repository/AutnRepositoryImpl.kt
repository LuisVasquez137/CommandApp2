package com.aplicacion.commandapp.data.repository

import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.domain.model.User
import com.aplicacion.commandapp.domain.repository.AuthRespository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AutnRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRespository {

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)

        }
    }

    override suspend fun register(user: User): Response<FirebaseUser> {
      return try {
          val result = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
          Response.Success(result.user!!)

      } catch (e: Exception){
          e.printStackTrace()
          Response.Failure(e)
      }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }


}