package com.kelompok6.smart_kids.ui.pages.reading

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.pages.introduction.AlphabetActivity
import com.kelompok6.smart_kids.ui.pages.memahami.MemahamiKataActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class ReadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                ReadingScreen(
                    onBackClick = { finish() },
                    onStartLettersClick = {
                        // Buka AlphabetActivity untuk belajar huruf
                        val intent = Intent(this@ReadingActivity, AlphabetActivity::class.java)
                        startActivity(intent)
                    },
                    onStartWordsClick = {
                        // Untuk sementara, arahkan ke AlphabetActivity juga
                        // Nanti bisa diganti ke halaman "Kata" terpisah
                        val intent = Intent(this@ReadingActivity, MemahamiKataActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}