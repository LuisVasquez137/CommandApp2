package com.aplicacion.commandapp.di

import com.aplicacion.commandapp.core.Constants.USERS
import com.aplicacion.commandapp.data.repository.AutnRepositoryImpl
import com.aplicacion.commandapp.data.repository.UsersRespositoryImpl
import com.aplicacion.commandapp.domain.repository.AuthRespository
import com.aplicacion.commandapp.domain.repository.UsersRepository
import com.aplicacion.commandapp.domain.use_cases.auth.AuthUseCases
import com.aplicacion.commandapp.domain.use_cases.auth.GetCurrentUser
import com.aplicacion.commandapp.domain.use_cases.auth.LogOut
import com.aplicacion.commandapp.domain.use_cases.auth.Login
import com.aplicacion.commandapp.domain.use_cases.auth.Register
import com.aplicacion.commandapp.domain.use_cases.users.Create
import com.aplicacion.commandapp.domain.use_cases.users.UsersUseCases
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

//Esto es para que funcione el caso de uso Login, ya que lo inyectamos de parte del repositorio y si no no funciona
    @Provides
    fun provideAuthRepository(impl: AutnRepositoryImpl): AuthRespository = impl


    //para que funcione la clase de casos de uso
    @Provides
    fun provideAuthUseCases(repository: AuthRespository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = LogOut(repository),
        register = Register(repository)
    )


    //Esto es para la parte de firestore
    @Provides
    fun profideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun porvideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    //Aqui termina la parte de firestore


    //para inyectar el repositorio, esto se hace para que funcione el caso de uso Create, ya que lo inyectamos de parte del repositorio y si no no funciona
    @Provides
    fun provideUsersRepository(impl: UsersRespositoryImpl): UsersRepository = impl

    //para que funcione la clase de casos de uso
    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository)
    )



}
