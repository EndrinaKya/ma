package com.kelompok6.smart_kids.ui.pages.memahami

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class MemahamiKataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageResId = intent.getIntExtra("image_res", 0).takeIf { it != 0 }

        setContent {
            Smart_KidsTheme {
                MemahamiKataScreen(
                    onBackClick = { finish() },
                    onMicClick = {
                        // TODO: Speech recognition
                    },
                    wordImageResId = imageResId // ✅
                )
            }
        }
    }
}