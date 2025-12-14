package com.kelompok6.smart_kids.ui.pages.writingWords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class WritingWordsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                WritingWordsScreenContent(
                    onBackClick = { finish() },
                    correctAnswer = "KUDA", // Sesuaikan dengan soal yang ditampilkan
                    wordImageResId = null   // Ganti dengan R.drawable.nama_gambar jika ada
                )
            }
        }
    }
}