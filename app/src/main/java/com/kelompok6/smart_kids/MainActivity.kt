package com.kelompok6.smart_kids.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kelompok6.smart_kids.ui.pages.login.LoginScreen
import com.kelompok6.smart_kids.ui.pages.register.RegisterScreen
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import com.kelompok6.smart_kids.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "splash") {

                    composable("splash") {
                        SplashScreen(
                            onLogoClick = {
                                navController.navigate("register") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("register") {
                        RegisterScreen(
                            onNavigateToLogin = {
                                // Navigasi ke login
                                navController.navigate("login") {
                                    popUpTo("register") { inclusive = true }
                                }
                            },
                            onRegisterClick = {
                                // Mock: langsung anggap sukses → ke login
                                navController.navigate("login") {
                                    popUpTo("register") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("login") {
                        LoginScreen(
                            onRegisterClick = {
                                navController.navigate("register")
                            },
                            onLoginClick = { email, password ->
                                // Validasi sederhana
                                if (email.isNotEmpty() && password.isNotEmpty()) {

                                }
                            }
                        )
                    }

                }
            }
        }
    }
}

// --- SplashScreen tetap sama ---
@Composable
fun SplashScreen(onLogoClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9AD6A5)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.smartkids),
                contentDescription = "Logo Smart Kids",
                modifier = Modifier
                    .size(160.dp)
                    .clickable { onLogoClick() }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "SMART KIDS",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onLogoClick = {})
}