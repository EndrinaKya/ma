package com.kelompok6.smart_kids.ui.pages.writing

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.kelompok6.smart_kids.ui.pages.writingLetters.WritingLettersActivity
import com.kelompok6.smart_kids.ui.pages.writingWords.WritingWordsActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class WritingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                WritingScreen(
                    onStartLettersClick = {
                        // Buka Activity untuk Menulis Huruf
                        val intent = Intent(this@WritingActivity, WritingLettersActivity::class.java)
                        startActivity(intent)
                    },
                    onStartWordsClick = {
                        // Buka Activity untuk Menulis Kata
                        val intent = Intent(this@WritingActivity, WritingWordsActivity::class.java)
                        startActivity(intent)
                    },
                    onBackClick = {
                        // Aksi untuk tombol kembali di TopAppBar
                        finish()
                    }
                )
            }
        }
    }
}