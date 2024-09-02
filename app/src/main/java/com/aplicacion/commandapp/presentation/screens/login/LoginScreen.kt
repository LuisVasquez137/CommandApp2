package com.aplicacion.commandapp.presentation.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

import com.aplicacion.commandapp.R
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.presentation.navigation.AppScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen( navController: NavHostController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val loginFlow = loginViewModel.loginFlow.collectAsState()


    val focusManager = LocalFocusManager.current
    val validateEmail = loginViewModel.validateEmail()
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()

                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.meal2), // Reemplaza con tu logo
                    contentDescription = "Logo",
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Email Field

                OutlinedTextField(
                    value = loginViewModel.email.value,
                    onValueChange = {
                        loginViewModel.email.value = it
                        loginViewModel.validateEmail()
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = loginViewModel.emailError.value,
                    color = Color.Red,
                    textAlign = TextAlign.Left,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Field

                OutlinedTextField(
                    value = loginViewModel.password.value,
                    onValueChange = {
                        loginViewModel.password.value = it
                        loginViewModel.validatePassword()
                    },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = loginViewModel.passwordError.value,
                    color = Color.Red,
                    textAlign = TextAlign.Left,
                    fontSize = 8.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                //Esta condicion indica que la variable loginResult mostrara que los datos no son los correctos


                // Login Button
                Button(
                    onClick = {
                        loginViewModel.login()

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    enabled = loginViewModel.isEnableLoginButton


                ) {
                    Text(text = "Log In")
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))

                // Google Login Button
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google), // Reemplaza con el ícono de Google
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Sign in with Google")
                }


            }
        },


        bottomBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp), horizontalArrangement = Arrangement.Center
            ) {
                Text(

                    text = "No tienes cuenta?"


                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Registrate",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AppScreen.RegisterScreen.route)

                        },

                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )


            }


        }
    )

    loginFlow.value?.let {
        when (it) {
            //Mostrar cuando se este cargando
            Response.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()

                }
            }

            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(AppScreen.ProfileScreen.route) {
                        popUpTo(AppScreen.LoginScreen.route) { inclusive = true }

                    }
                }

            }

            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
        }
    }
}





