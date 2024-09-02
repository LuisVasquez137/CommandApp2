package com.aplicacion.commandapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {
    //para el email
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailError: MutableState<String> = mutableStateOf("")

    //contraseña
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var password: MutableState<String> = mutableStateOf("")
    var passwordError: MutableState<String> = mutableStateOf("")

    var isEnableLoginButton = false


    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: MutableStateFlow<Response<FirebaseUser>?> = _loginFlow


    val currentUser = authUseCases.getCurrentUser()
    init {
        if(currentUser != null){ //Sesion iniciada
            _loginFlow.value = Response.Success(currentUser)
        }

    }



    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCases.login(email.value, password.value)
        _loginFlow.value = result
    }

    fun enableLoginButton() {
        isEnableLoginButton = isEmailValid.value && isPasswordValid.value
    }


    fun validateEmail() {
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailError.value = ""
        }else{
            isEmailValid.value = false
            emailError.value = "Email no valido"
        }
        enableLoginButton()
    }
    fun validatePassword() {
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            passwordError.value = ""
        }
        else{
            isPasswordValid.value = false
            passwordError.value = "La contraseña debe tener al menos 6 caracteres"

        }
        enableLoginButton()
    }
}