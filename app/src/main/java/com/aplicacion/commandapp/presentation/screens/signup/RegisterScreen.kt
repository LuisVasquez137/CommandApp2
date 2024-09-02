package com.aplicacion.commandapp.presentation.screens.signup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.aplicacion.commandapp.R
import com.aplicacion.commandapp.domain.model.Response
import com.aplicacion.commandapp.presentation.navigation.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen( navController: NavHostController,registerViewModel: RegisterViewModel = hiltViewModel()) {
    val focusManager = LocalFocusManager.current

    val registerFlow = registerViewModel.registerFlow.collectAsState()



    Scaffold(
        content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.meal2), // Reemplaza con tu logo
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(200.dp)

                        .padding(bottom = 32.dp),
                    contentScale = ContentScale.Fit
                )

                //Aqui se ingresa el codigo del restaurante
                OutlinedTextField(
                    value = registerViewModel.idRestaurant.value,
                    onValueChange = {registerViewModel.idRestaurant.value = it
                        },
                    label = { Text("Codigo del restaurante") },
                    placeholder = { Text("Codigo recibido por el administrador") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Name Icon"
                        )
                    }
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Name Field

                OutlinedTextField(
                    value = registerViewModel.username.value ,
                    onValueChange = {registerViewModel.username.value = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Name Icon"
                        )
                    }
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Email

                OutlinedTextField(
                    value = registerViewModel.email.value,
                    onValueChange = {registerViewModel.email.value = it
                        registerViewModel.validateEmail()},
                    label = { Text("Correo electronico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon"
                        )
                    }
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Password Field

                OutlinedTextField(
                    value =registerViewModel.password.value,
                    onValueChange = { registerViewModel.password.value = it
                        registerViewModel.validatePassword()},
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon"
                        )
                    }
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password Field

                OutlinedTextField(
                    value = registerViewModel.confirmPassword.value,
                    onValueChange = { registerViewModel.confirmPassword.value = it
                        registerViewModel.validateConfirmPassword()},
                    label = { Text("Confirmar contraseña") },
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
                            contentDescription = "Confirm Password Icon"
                        )
                    }
                )



                Spacer(modifier = Modifier.height(16.dp))

                // Register Button
                Button(
                    onClick = {
                        registerViewModel.onRegister()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = registerViewModel.isEnableRegisterButton
                ) {
                    Text(text = "Registrarse")
                }



                // Login Link

            }

        },
        bottomBar = {

          /*  Row (modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp), horizontalArrangement = Arrangement.Center) {
                Text(

                    text = "Ya tienes cuenta?"


                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Registrate",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AppScreen.LoginScreen.route)



                        },

                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline)


            }*/

        }
    )

    registerFlow.value?.let {
        when(it){
            Response.Loading -> {
                // Mostrar un indicador de carga
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
            is Response.Success -> {
               LaunchedEffect(Unit){
                   registerViewModel.createUser()
                   navController.popBackStack(AppScreen.LoginScreen.route, true)
                   navController.navigate(AppScreen.ProfileScreen.route){

                   }
               }

            }
            is Response.Failure -> {
                // Mostrar un mensaje de error
                Toast.makeText(navController.context, it.exception?.message, Toast.LENGTH_LONG).show()
            }

            else -> {}
        }
    }

}