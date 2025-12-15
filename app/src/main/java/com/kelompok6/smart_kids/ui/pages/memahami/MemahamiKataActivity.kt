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
        setContent {
            Smart_KidsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MemahamiKataScreen(
                        onBackClick = { finish() },
                        onMicClick = {
                            // TODO: Implementasi mic
                        },
                        letterImageResId = null // atau ambil dari intent
                    )
                }
            }
        }
    }
}