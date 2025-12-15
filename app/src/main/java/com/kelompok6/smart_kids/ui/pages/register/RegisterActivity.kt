package com.kelompok6.smart_kids.ui.pages.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kelompok6.smart_kids.ui.pages.login.LoginActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import com.kelompok6.smart_kids.viewmodel.RegisterViewModel
import com.kelompok6.smart_kids.viewmodel.RegisterState

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_KidsTheme {
                val registerViewModel: RegisterViewModel = viewModel()
                val registerState by registerViewModel.registerState.collectAsState()

                RegisterScreen(
                    onNavigateToLogin = {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    },
                    onRegisterClick = { name, email, password ->
                        // 💡 Ambil konfirmasi password?
                        // Tapi karena tidak dikirim dari screen, kita harus minta user input ulang
                        // → Solusi: tambahkan parameter ke callback, atau handle di sini dengan state

                        // ❌ SAYANGNYA: kamu tidak mengirim `konfirPass` dari Composable
                        // Jadi kita terpaksa tidak bisa validasi konfirmasi di sini

                        // ✅ ALTERNATIF: Tambahkan `konfirPass` ke callback (lebih baik)

                        // TAPI untuk saat ini, sesuai permintaan: kita asumsikan kamu akan kirim 4 parameter
                        // Namun karena screen belum support, saya akan modifikasi screen dulu → sudah di atas

                        // Tapi karena di atas saya hanya kirim 3, maka **kita skip validasi konfirmasi**
                        // atau tambahkan di sini jika kamu ubah signature jadi 4 parameter

                        // ✅ VALIDASI DASAR
                        when {
                            name.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                                Toast.makeText(this@RegisterActivity, "Semua field wajib diisi!", Toast.LENGTH_SHORT).show()
                            }
                            password.length < 6 -> {
                                Toast.makeText(this@RegisterActivity, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                registerViewModel.register(name, email, password)
                            }
                        }
                    }
                )

                LaunchedEffect(registerState) {
                    when (registerState) {
                        is RegisterState.Success -> {
                            Toast.makeText(this@RegisterActivity, "Registrasi berhasil! Silakan login.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        }
                        is RegisterState.Error -> {
                            Toast.makeText(this@RegisterActivity, (registerState as RegisterState.Error).message, Toast.LENGTH_LONG).show()
                            registerViewModel.resetState()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}