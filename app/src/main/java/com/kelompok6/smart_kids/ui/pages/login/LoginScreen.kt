package com.kelompok6.smart_kids.ui.pages.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import com.kelompok6.smart_kids.viewmodel.LoginState   // ✅ IMPORT

@Composable
fun LoginScreen(
    loginState: LoginState, // ✅ TAMBAH INI
    onRegisterClick: () -> Unit,
    onLoginClick: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFA5D6A7))
    ) {
        IconButton(
            onClick = onRegisterClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Kembali",
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            Image(
                painter = painterResource(id = R.drawable.smartlogin),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text("Hello!!", fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text("Selamat Datang di Smart Kids", fontSize = 14.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text("Login", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(30.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
                    enabled = loginState != LoginState.Loading, // ✅ disable saat loading
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )

                Spacer(Modifier.height(30.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
                    enabled = loginState != LoginState.Loading, // ✅
                    visualTransformation =
                        if (isPasswordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                painter = painterResource(
                                    id = if (isPasswordVisible) R.drawable.pwon else R.drawable.pwoff
                                ),
                                contentDescription = "Toggle password",
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
            }

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { onLoginClick(email, password) },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                enabled = loginState != LoginState.Loading, // ✅
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDBEFDC),
                    contentColor = Color.Black
                )
            ) {
                if (loginState == LoginState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("LOGIN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Belum mempunyai akun? ", fontSize = 12.sp)
                Text(
                    "Register",
                    fontSize = 12.sp,
                    color = Color.Blue,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    Smart_KidsTheme {
        LoginScreen(
            loginState = LoginState.Idle,
            onRegisterClick = {},
            onLoginClick = { _, _ -> }
        )
    }
}
