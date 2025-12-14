package com.kelompok6.smart_kids.ui.pages.introduction

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.pages.mengucapkan.MengucapkanHurufActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class AlphabetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                AlphabetScreen(
                    onBackClick = {
                        finish()
                    },
                    onNextClick = {
                        // ✅ Buka MengucapkanHurufActivity
                        val intent = Intent(this@AlphabetActivity, MengucapkanHurufActivity::class.java)
                        startActivity(intent)
                        // Opsional: finish() jika tidak ingin kembali ke AlphabetScreen
                        // finish()
                    }
                )
            }
        }
    }
}