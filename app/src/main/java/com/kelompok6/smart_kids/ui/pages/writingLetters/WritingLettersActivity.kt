package com.kelompok6.smart_kids.ui.pages.writingLetters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class WritingLetterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                WritingLetterScreenContent(
                    onBackClick = { finish() }
                )
            }
        }
    }
}