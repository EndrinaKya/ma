package com.kelompok6.smart_kids.ui.pages.introduction

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.kelompok6.smart_kids.ui.pages.mengucapkan.MengucapkanHurufActivity
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import com.kelompok6.smart_kids.viewmodel.AlphabetViewModel
import com.kelompok6.smart_kids.viewmodel.AlphabetListState
import com.kelompok6.smart_kids.viewmodel.AlphabetSoundState

class AlphabetActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Smart_KidsTheme {
                val viewModel: AlphabetViewModel = viewModel()
                val listState by viewModel.alphabetListState.collectAsState()
                val soundState by viewModel.alphabetSoundState.collectAsState()

                // Ambil daftar huruf saat pertama kali
                LaunchedEffect(Unit) {
                    viewModel.fetchAlphabetList()
                }

                // Amati state suara
                LaunchedEffect(soundState) {
                    when (val state = soundState) { // ✅ Hindari smart cast error
                        is AlphabetSoundState.Success -> {
                            playSound(state.audioUrl)
                            viewModel.resetSoundState()
                        }
                        is AlphabetSoundState.Error -> {
                            Toast.makeText(this@AlphabetActivity, state.message, Toast.LENGTH_SHORT).show()
                            viewModel.resetSoundState()
                        }
                        else -> Unit
                    }
                }

                // Render UI berdasarkan state daftar huruf
                when (val state = listState) {
                    is AlphabetListState.Success -> {
                        AlphabetScreen(
                            alphabetList = state.list,
                            onBackClick = { finish() },
                            onNextClick = {
                                startActivity(Intent(this@AlphabetActivity, MengucapkanHurufActivity::class.java))
                            },
                            onLetterClick = { letter ->
                                viewModel.fetchSoundUrl(letter)
                            }
                        )
                    }

                    is AlphabetListState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is AlphabetListState.Error -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Gagal memuat huruf", fontSize = 18.sp)
                        }
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun playSound(url: String) {
        mediaPlayer?.run { stop(); release() }
        mediaPlayer = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val mp = MediaPlayer().apply {
                    setDataSource(url)
                    prepare() // ← Ini yang sering gagal
                }
                withContext(Dispatchers.Main) {
                    mp.start()
                    mediaPlayer = mp
                }
            } catch (e: Exception) {
                e.printStackTrace() // 🔥 Lihat di Logcat!
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AlphabetActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}