package com.kelompok6.smart_kids.ui.pages.editprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

@Composable
fun ProfileEditScreen(
    onBackClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Gunakan Surface sebagai background utama
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFA5D6A7)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter // agar konten mulai dari atas, tapi tetap di tengah horizontal
        ) {
            // Header: tombol back (tanpa TopAppBar)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Kembali",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // Konten utama di tengah
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .verticalScroll(rememberScrollState())
                    .padding(top = 80.dp, bottom = 32.dp), // 80dp untuk kasih ruang di bawah header
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Avatar
                Image(
                    painter = painterResource(id = R.drawable.profil),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )

                // Input Nama
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
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

                // Input Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val eyeImage = painterResource(id = R.drawable.pwoff)
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Image(
                                painter = eyeImage,
                                contentDescription = if (passwordVisible) "Sembunyikan password" else "Tampilkan password",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
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

                // Input Konfirmasi Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Konfirmasi Password") },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val eyeImage = painterResource(id = R.drawable.pwoff)
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Image(
                                painter = eyeImage,
                                contentDescription = if (confirmPasswordVisible) "Sembunyikan password" else "Tampilkan password",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(25.dp),
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

                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .width(180.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDBEFDC),
                        contentColor = Color.Black
                    )
                ) {
                    Text("LOGIN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileEditScreen() {
    Smart_KidsTheme {
        ProfileEditScreen(onBackClick = {})
    }
}