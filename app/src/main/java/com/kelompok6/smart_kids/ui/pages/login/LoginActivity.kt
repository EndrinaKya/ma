package com.kelompok6.smart_kids.ui.pages.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.kelompok6.smart_kids.ui.pages.home.HomeActivity
import com.kelompok6.smart_kids.ui.pages.register.RegisterActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_KidsTheme {
                // ✅ Deklarasi semua state di sini
                var navigateToRegister by remember { mutableStateOf(false) }
                var navigateToHome by remember { mutableStateOf(false) }

                // ✅ Tampilkan LoginScreen
                LoginScreen(
                    onRegisterClick = {
                        navigateToRegister = true
                    },
                    onLoginClick = { email, password ->
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Email dan password tidak boleh kosong",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (password.length < 6) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Password minimal 6 karakter",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            navigateToHome = true
                        }
                    }
                )

                // ✅ Navigasi ke RegisterActivity
                LaunchedEffect(navigateToRegister) {
                    if (navigateToRegister) {
                        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                        startActivity(intent)
                        navigateToRegister = false
                    }
                }

                // ✅ Navigasi ke HomeActivity
                LaunchedEffect(navigateToHome) {
                    if (navigateToHome) {
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                        navigateToHome = false
                    }
                }
            }
        }
    }
}