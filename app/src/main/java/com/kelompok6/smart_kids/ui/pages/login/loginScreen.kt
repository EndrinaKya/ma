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


@Composable
fun LoginScreen(onRegisterClick: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            Text("HELLO!!",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White)
            Text("Selamat Datang di Smart Kids",
                fontSize = 14.sp,
                color = Color.White)

        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("LOGIN", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it }, //it = update variabel email dengan nilai baru
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

            Spacer(Modifier.height(15.dp))

            Text(
                text = "Lupa Kata Sandi?",
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = { },
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
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onRegisterClick = {})
}
