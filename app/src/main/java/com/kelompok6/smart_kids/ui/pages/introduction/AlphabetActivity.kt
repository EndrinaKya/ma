package com.kelompok6.smart_kids.ui.pages.introduction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class AlphabetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                AlphabetScreen(
                    onBackClick = {
                        // Kembali ke activity sebelumnya (misal: HomeActivity atau halaman sebelumnya)
                        finish()
                    },
                    onNextClick = {
                        // Navigasi ke halaman berikutnya (misal: halaman kata, game, dll.)
                        // Contoh:
                        // val intent = Intent(this@AlphabetActivity, NextActivity::class.java)
                        // startActivity(intent)
                        // finish()
                    }
                )
            }
        }
    }
}