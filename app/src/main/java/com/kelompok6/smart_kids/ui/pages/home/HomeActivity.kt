package com.kelompok6.smart_kids.ui.pages.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kelompok6.smart_kids.ui.pages.editprofile.EditProfileActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import com.kelompok6.smart_kids.ui.pages.writing.WritingActivity
import com.kelompok6.smart_kids.ui.pages.reading.ReadingActivity
import com.kelompok6.smart_kids.ui.pages.introduction.AlphabetActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smart_KidsTheme {
                HomeScreen { menuItem ->
                    when (menuItem) {
                        "home" -> {
                            // Sudah di halaman home, tidak perlu aksi
                        }
                        "edit_profile" -> {
                            val intent = Intent(this@HomeActivity, EditProfileActivity::class.java)
                            startActivity(intent)
                        }
                        "reading" -> {
                            val intent = Intent(this@HomeActivity, ReadingActivity::class.java)
                            startActivity(intent)
                        }
                        "writing" -> {
                            val intent = Intent(this@HomeActivity, WritingActivity::class.java)
                            startActivity(intent)
                        }
                        "introduction" -> {
                            val intent = Intent(this@HomeActivity, AlphabetActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}