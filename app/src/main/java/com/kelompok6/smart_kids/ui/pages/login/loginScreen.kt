package com.kelompok6.smart_kids

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.style.TextAlign
import com.kelompok6.smart_kids.viewmodel.LoginState
import com.kelompok6.smart_kids.viewmodel.LoginViewModel



@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA5D6A7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.smartlogin),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(160.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                "HELLO KIDS kids!!",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                "Selamat Datang di Smart Kids",
                fontSize = 14.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("LOGIN", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors( 
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

        // 🔥 ERROR / LOADING / SUCCESS ditampilkan DI ATAS BUTTON
        when (state) {
            is LoginState.Loading -> {
                Text("Loading...", color = Color.White)
                Spacer(Modifier.height(8.dp))
            }
            is LoginState.Error -> {
                Text(
                    (state as LoginState.Error).message,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
            }
            is LoginState.Success -> {
                Text(
                    text = "Login Berhasil!",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
            }
            else -> {}
        }

        // 🔥 Tombol Login
        Button(
            onClick = {
                viewModel.login(email, password)
            },
            modifier = Modifier
                .width(180.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDBEFDC),
                contentColor = Color.Black
            )
        ) {
            Text("LOGIN", fontSize = 16.sp)
        }

        Spacer(Modifier.height(15.dp))

        Text(
            text = "Belum mempunyai akun? Register?",
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}




