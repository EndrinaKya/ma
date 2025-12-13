package com.kelompok6.smart_kids.ui.pages.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.kelompok6.smart_kids.ui.pages.register.RegisterActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_KidsTheme {
                var navigateToRegister by remember { mutableStateOf(false) }
                var navigateToHome by remember { mutableStateOf(false) }

                LoginScreen(
                    onRegisterClick = {
                        navigateToRegister = true
                    },
                    onLoginClick = { email, password ->
                        // ✅ Validasi sederhana (bisa dikembangkan)
                        if (email.isEmpty() || password.isEmpty()) {
                            // Tampilkan pesan error
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
                            // Anggap login berhasil → navigasi ke halaman utama
                            navigateToHome = true
                        }
                    }
                )

                // Navigasi ke Register
                LaunchedEffect(navigateToRegister) {
                    if (navigateToRegister) {
                        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                        startActivity(intent)
                        // Tidak perlu finish(), agar user bisa kembali
                        navigateToRegister = false
                    }
                }

                // Navigasi ke halaman utama (misal MainActivity)
                LaunchedEffect(navigateToHome) {
                    if (navigateToHome) {
                        // Ganti dengan aktivitas utama kamu (misal HomeActivity)
                        // Contoh:
                        // val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        // startActivity(intent)
                        // finish()

                        // Untuk sementara, kita hanya tampilkan toast
                        Toast.makeText(
                            this@LoginActivity,
                            "Login berhasil! (Navigasi ke beranda belum diimplementasi)",
                            Toast.LENGTH_LONG
                        ).show()

                        // Jika kamu punya MainActivity, ganti kode di atas dengan:
                        // val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        // startActivity(intent)
                        // finish()

                        navigateToHome = false
                    }
                }
            }
        }
    }
}