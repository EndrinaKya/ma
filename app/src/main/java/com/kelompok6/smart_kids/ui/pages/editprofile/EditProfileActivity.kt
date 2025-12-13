package com.kelompok6.smart_kids.ui.pages.editprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

class EditProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                ProfileEditScreen(
                    onBackClick = {
                        // Kembali ke activity sebelumnya (misal: HomeActivity)
                        finish()
                    }
                )
            }
        }
    }
}