package com.kelompok6.smart_kids.ui.pages.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kelompok6.smart_kids.ui.pages.home.HomeActivity
import com.kelompok6.smart_kids.ui.pages.register.RegisterActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import com.kelompok6.smart_kids.viewmodel.LoginViewModel
import com.kelompok6.smart_kids.viewmodel.LoginState

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_KidsTheme {
                val loginViewModel: LoginViewModel = viewModel()
                val loginState by loginViewModel.loginState.collectAsState()

                LoginScreen(
                    loginState = loginState,
                    onRegisterClick = {
                        startActivity(Intent(this, RegisterActivity::class.java))
                    },
                    onLoginClick = { email, password ->
                        when {
                            email.isEmpty() || password.isEmpty() -> {
                                Toast.makeText(
                                    this,
                                    "Email dan password tidak boleh kosong",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            password.length < 6 -> {
                                Toast.makeText(
                                    this,
                                    "Password minimal 6 karakter",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            else -> {
                                loginViewModel.login(email, password)
                            }
                        }
                    }
                )

                // ✅ Amati perubahan state
                LaunchedEffect(loginState) {
                    when (loginState) {
                        is LoginState.Success -> {
                            // ✅ Langsung navigasi, jangan reset state
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                            // ❌ Jangan panggil resetState() di sini
                        }

                        is LoginState.Error -> {
                            Toast.makeText(
                                this@LoginActivity,
                                (loginState as LoginState.Error).message,
                                Toast.LENGTH_SHORT
                            ).show()
                            // ✅ Reset hanya untuk error agar toast tidak muncul ulang
                            loginViewModel.resetState()
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}