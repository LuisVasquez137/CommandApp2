package com.aplicacion.commandapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.domain.model.User
import com.aplicacion.commandapp.domain.use_cases.auth.AuthUseCases
import com.aplicacion.commandapp.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    var idRestaurant: MutableState<String> = mutableStateOf("")
    var isIdRestaurantValid: MutableState<Boolean> = mutableStateOf(false)
    var idRestarantError: MutableState<String> = mutableStateOf("")

    //USERNAME
    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameError: MutableState<String> = mutableStateOf("")


    //EMAIL
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailError: MutableState<String> = mutableStateOf("")

    //PASSWORD
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordError: MutableState<String> = mutableStateOf("")

    //CONFIRM PASSWORD
    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordError: MutableState<String> = mutableStateOf("")

    var isEnableRegisterButton = false

    private val _registerFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val registerFlow: MutableStateFlow<Response<FirebaseUser>?> = _registerFlow

    var user = User()

    fun onRegister() {
        user.username = username.value
        user.email = email.value
        user.password = password.value
        user.idRestaurant = idRestaurant.value

        register(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }

    fun register(user: User) = viewModelScope.launch {
        _registerFlow.value = Response.Loading
        val result = authUseCases.register(user)
        _registerFlow.value = result
    }


    fun enableLoginButton() {
        isEnableRegisterButton = isEmailValid.value && isPasswordValid.value

    }

    fun validateConfirmPassword() {
        if (password.value == confirmPassword.value) {
            isConfirmPasswordValid.value = true
            confirmPasswordError.value = ""
        } else {
            isConfirmPasswordValid.value = false
            confirmPasswordError.value = "Las contraseñas no coinciden"
        }
        enableLoginButton()
    }


    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailError.value = ""
        } else {
            isEmailValid.value = false
            emailError.value = "Email no valido"
        }
        enableLoginButton()
    }

    fun validatePassword() {
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            passwordError.value = ""
        } else {
            isPasswordValid.value = false
            passwordError.value = "La contraseña debe tener al menos 6 caracteres"

        }
        enableLoginButton()
    }


}