package com.kelompok6.smart_kids

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.kelompok6.smart_kids.ui.pages.home.HomeActivity
import com.kelompok6.smart_kids.ui.pages.login.LoginActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Contoh cek sesi (nanti bisa diisi)
        val isLoggedIn = false // <-- ganti dengan logic sesi sesungguhnya

        val targetActivity = if (isLoggedIn) {
            HomeActivity::class.java
        } else {
            LoginActivity::class.java
        }

        startActivity(Intent(this, targetActivity))
        finish()
    }
}
