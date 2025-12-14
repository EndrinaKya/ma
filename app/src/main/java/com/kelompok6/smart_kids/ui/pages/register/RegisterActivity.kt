package com.kelompok6.smart_kids.ui.pages.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.kelompok6.smart_kids.ui.pages.login.LoginActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_KidsTheme {
                var navigateToLogin by remember { mutableStateOf(false) }

                RegisterScreen(
                    onNavigateToLogin = {
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    },
                    onRegisterClick = {
                        navigateToLogin = true
                    }
                )

                LaunchedEffect(navigateToLogin) {
                    if (navigateToLogin) {
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}