package com.aplicacion.commandapp.presentation.screens.profile

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aplicacion.commandapp.R
import com.aplicacion.commandapp.presentation.navigation.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profielViewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {},
        content = {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {
                    Image(

                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        painter = painterResource(id = R.drawable.fondo_perfil),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        alpha = 0.5f
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "Bienvenido",
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(75.dp))
                        Image(
                            modifier = Modifier
                                .size(155.dp)
                                .clip(CircleShape)
                                .border(width = 4.dp, color = Color.Green),
                            painter = painterResource(id = R.drawable.perfil),
                            contentDescription = ""
                        )

                    }
                }
                Spacer(modifier = Modifier.height(55.dp))
                Text(
                    text = "Nombre de usuario:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Email de usuario:",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {}, modifier = Modifier.fillMaxWidth(0.8f)){
                    Text(text = "Editar Perfil")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    profielViewModel.logout()
                    navController.navigate(AppScreen.LoginScreen.route) {
                        popUpTo(AppScreen.ProfileScreen.route) { inclusive = true }
                    }
                }, modifier = Modifier.fillMaxWidth(0.8f)) {
                    Text(text = "Cerrar Sesion")

                }


            }

        },
        bottomBar = {}

    )
}