package com.kelompok6.smart_kids.ui.pages.writing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class WritingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                WritingScreen(
                    onBackClick = {
                        // Kembali ke activity sebelumnya (misal: HomeActivity)
                        finish()
                    }
                )
            }
        }
    }
}