package com.kelompok6.smart_kids.ui.pages.reading

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.pages.introduction.AlphabetActivity
import com.kelompok6.smart_kids.ui.pages.memahami.MemahamiKataActivity // ✅ Tambahkan impor ini
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import com.kelompok6.smart_kids.R

class ReadingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                ReadingScreen(
                    onBackClick = { finish() },
                    onStartLettersClick = {
                        // ✅ HURUF → Alphabet
                        val intent = Intent(this@ReadingActivity, AlphabetActivity::class.java)
                        startActivity(intent)
                    },
                    onStartWordsClick = {
                        val intent = Intent(this@ReadingActivity, MemahamiKataActivity::class.java).apply {
                            putExtra("image_res", R.drawable.pencil) // atau gambar lain
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}