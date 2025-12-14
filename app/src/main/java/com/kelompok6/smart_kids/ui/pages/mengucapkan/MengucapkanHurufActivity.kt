package com.kelompok6.smart_kids.ui.pages.mengucapkan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class MengucapkanHurufActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val letter = intent.getStringExtra("letter") ?: "A"

        setContent {
            Smart_KidsTheme {
                MengucapkanHurufScreen(
                    onBackClick = { finish() },
                    onMicClick = {
                        // TODO: rekam suara untuk huruf `letter`
                    },
                    // Jika Anda punya gambar untuk huruf A, B, dll:
                    // letterImageResId = when(letter) {
                    //     "A" -> R.drawable.letter_a
                    //     "B" -> R.drawable.letter_b
                    //     else -> null
                    // }
                )
            }
        }
    }
}