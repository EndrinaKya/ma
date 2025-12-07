package com.kelompok6.smart_kids.ui.pages.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kelompok6.smart_kids.viewmodel.RegisterState
import com.kelompok6.smart_kids.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit, // ✅ Tanpa parameter (karena langsung ke login)
    viewModel: RegisterViewModel = viewModel()
) {
    var namaLengkap by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var konfirPass by remember { mutableStateOf("") }

    val registerState by viewModel.registerState.collectAsState()

    // Handle sukses → panggil callback tanpa token
    LaunchedEffect(registerState) {
        if (registerState is RegisterState.Success) {
            onRegisterSuccess() // ✅ Sesuai tipe: () -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA5D6A7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                "Register Akun",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(25.dp))

            OutlinedTextField(
                value = namaLengkap,
                onValueChange = { namaLengkap = it },
                placeholder = { Text("Nama Lengkap") },
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
                visualTransformation = PasswordVisualTransformation(),
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
                value = konfirPass,
                onValueChange = { konfirPass = it },
                placeholder = { Text("Konfirmasi Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            // Tampilkan error jika ada
            if (registerState is RegisterState.Error) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = (registerState as RegisterState.Error).message,
                    color = Color.Red,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(24.dp))

        val isLoading = registerState is RegisterState.Loading

        Button(
            onClick = {
                if (namaLengkap.isEmpty()) {
                    viewModel._registerState.value = RegisterState.Error("Nama lengkap harus diisi")
                    return@Button
                }
                if (email.isEmpty()) {
                    viewModel._registerState.value = RegisterState.Error("Email harus diisi")
                    return@Button
                }
                if (password.isEmpty()) {
                    viewModel._registerState.value = RegisterState.Error("Password harus diisi")
                    return@Button
                }
                if (password != konfirPass) {
                    viewModel._registerState.value = RegisterState.Error("Password tidak cocok")
                    return@Button
                }

                viewModel.register(namaLengkap, email, password)
            },
            enabled = !isLoading,
            modifier = Modifier
                .width(180.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isLoading) Color.Gray else Color(0xFFDBEFDC),
                contentColor = Color.Black
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.Black,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Text("DAFTAR", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.height(15.dp))

        Text(
            text = "Sudah mempunyai akun? Login",
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToLogin() },
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
    }
}