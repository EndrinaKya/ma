package com.kelompok6.smart_kids.ui.pages.mengucapkan

import com.kelompok6.smart_kids.R
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import java.util.Locale

class MengucapkanHurufActivity : ComponentActivity() {

    private val alphabet = ('A'..'Z').toList()

    // --- State Variables (Original Structure) ---
    private val isListening = mutableStateOf(false)
    private val recognizedText = mutableStateOf("")
    private val expectedLetter = mutableStateOf("A")
    private val hasResult = mutableStateOf(false) // apakah sudah ada hasil suara?
    private val isCorrect = mutableStateOf(false)

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (!granted) {
                Toast.makeText(
                    this@MengucapkanHurufActivity,
                    "Izin mikrofon diperlukan",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRandomLetter()
        checkPermission()
        setupSpeechRecognizer()

        setContent {
            Smart_KidsTheme {
                // Menggunakan komponen Compose (MengucapkanHurufScreen) yang disesuaikan dengan state Anda
                MengucapkanHurufScreen(
                    expectedLetter = expectedLetter.value,
                    letterImageResId = letterToDrawable(expectedLetter.value),
                    isListening = isListening.value,
                    previewText = recognizedText.value,
                    hasResult = hasResult.value,
                    isCorrect = isCorrect.value,
                    onBackClick = { finish() },
                    onMicClick = {
                        if (isListening.value) {
                            speechRecognizer.stopListening()
                        } else {
                            if (ContextCompat.checkSelfPermission(
                                    this@MengucapkanHurufActivity,
                                    Manifest.permission.RECORD_AUDIO
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Reset state sebelum merekam
                                recognizedText.value = ""
                                hasResult.value = false
                                isCorrect.value = false
                                speechRecognizer.startListening(recognizerIntent)
                            } else {
                                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                            }
                        }
                    },
                    onSubmitClick = {
                        // Hanya proses jika ada hasil
                        if (hasResult.value && recognizedText.value.isNotBlank()) {
                            checkResult(recognizedText.value)
                        }
                    },
                    onRetryClick = {
                        // Reset hasil dan mulai rekaman baru
                        recognizedText.value = ""
                        hasResult.value = false
                        isCorrect.value = false
                        if (ContextCompat.checkSelfPermission(
                                this@MengucapkanHurufActivity,
                                Manifest.permission.RECORD_AUDIO
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            speechRecognizer.startListening(recognizerIntent)
                        }
                    }
                )
            }
        }
    }

    private fun setRandomLetter() {
        // Fungsi ini akan dipanggil saat Activity dimulai atau saat jawaban benar
        val random = alphabet.random()
        expectedLetter.value = random.toString()
        recognizedText.value = ""
        isListening.value = false
        hasResult.value = false
        isCorrect.value = false
    }

    private fun setupSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                isListening.value = true
            }

            override fun onResults(results: Bundle?) {
                val text = results
                    ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull()
                isListening.value = false
                recognizedText.value = text ?: ""
                hasResult.value = !text.isNullOrBlank()

                // >>> PERUBAHAN LOGIKA DI SINI <<<
                if (!text.isNullOrBlank()) {
                    checkResult(text) // LANGSUNG LAKUKAN PENGECEKAN
                }
            }

            override fun onError(error: Int) {
                isListening.value = false
                recognizedText.value = ""
                hasResult.value = false
                isCorrect.value = false
                Log.e("STT", "Error $error")
                Toast.makeText(
                    this@MengucapkanHurufActivity,
                    "Gagal mendengar. Coba lagi.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onBeginningOfSpeech() {}
            override fun onEndOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale("id", "ID"))
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
        }
    }

    // FUNGSI CHECKRESULT YANG SUDAH DIPERKUAT
    private fun checkResult(text: String) {
        if (text.isBlank()) {
            Toast.makeText(
                this@MengucapkanHurufActivity,
                "Belum ada suara",
                Toast.LENGTH_SHORT
            ).show()
            isCorrect.value = false
            return
        }

        val expected = expectedLetter.value.lowercase(Locale.ROOT)
        var result = text.lowercase(Locale.ROOT)

        // Normalisasi: hapus spasi berlebih, tanda baca
        result = result.replace(Regex("[^a-zA-Z\\s]"), "").trim()

        // 1. Definisikan Base Keywords (Pengucapan)
        // Daftar ini hanya perlu berisi pengucapan intinya, bukan "huruf X"
        val baseKeywords: List<String> = when (expected) {
            "a" -> listOf("a", "ah", "apel", "alpha", "ay", "ei")
            "b" -> listOf("b", "be", "baca", "bola", "beta", "bee", "bi", "bii") // Ditambahkan 'bii'
            "c" -> listOf("c", "ce", "cat", "charlie", "see", "si", "ci", "sii") // Ditambahkan 'ci', 'sii'
            "d" -> listOf("d", "de", "dinding", "delta", "di", "dee")
            "e" -> listOf("e", "eh", "emas", "echo", "i", "ee")
            "f" -> listOf("f", "ef", "foto", "foxtrot", "eff", "ef")
            "g" -> listOf("g", "ge", "gigi", "golf", "ji", "gee")
            "h" -> listOf("h", "ha", "harimau", "hotel", "aitch", "ha")
            "i" -> listOf("i", "ih", "ikan", "india", "eye", "ai")
            "j" -> listOf("j", "je", "jalan", "juliett", "jay", "ji")
            "k" -> listOf("k", "ka", "kucing", "kilo", "kay", "kei")
            "l" -> listOf("l", "el", "lampu", "lima", "ell", "el")
            "m" -> listOf("m", "em", "meja", "mike", "em", "mi")
            "n" -> listOf("n", "en", "naga", "november", "en", "ne")
            "o" -> listOf("o", "oh", "obat", "oscar", "o", "ou")
            "p" -> listOf("p", "pe", "pena", "papa", "pee", "pi")
            "q" -> listOf("q", "ki", "kue", "quebec", "kyu", "cue")
            "r" -> listOf("r", "er", "roti", "romeo", "ar", "are")
            "s" -> listOf("s", "es", "sepatu", "sierra", "ess", "es")
            "t" -> listOf("t", "te", "topi", "tango", "ti", "tee")
            "u" -> listOf("u", "uh", "ular", "uniform", "you", "yu")
            "v" -> listOf("v", "vi", "video", "victor", "vee", "vi")
            "w" -> listOf("w", "we", "wajan", "whiskey", "double u", "dublu")
            "x" -> listOf("x", "ex", "xenon", "xray", "eks", "ex")
            "y" -> listOf("y", "ye", "yoga", "yankee", "why", "wi", "wai", "wye", "yey", "yei")
            "z" -> listOf("z", "zet", "zebra", "zulu", "zed", "zi")
            else -> listOf(expected)
        }

        // 2. Ekstrak Kata Kunci dari Hasil STT
        // Pisahkan kata, ambil elemen terakhir
        val resultWords = result.split(Regex("\\s+")).filter { it.isNotBlank() }
        val lastWord = resultWords.lastOrNull() ?: ""

        // Tambahan: Jika kata terakhir adalah 'huruf' atau 'kata', ambil kata kedua terakhir.
        // Ini mengatasi kasus seperti: "huruf" (STT berhenti di situ), atau jika ada noise.
        val relevantWord: String = if (lastWord == "huruf" || lastWord == "kata") {
            // Ambil kata kedua terakhir jika ada
            if (resultWords.size >= 2) resultWords[resultWords.size - 2] else lastWord
        } else {
            lastWord
        }

        // 3. Pengecekan
        // Cek apakah 'relevantWord' (kata kunci dari ucapan) ada di 'baseKeywords'
        val isCorrectFlag = baseKeywords.contains(relevantWord)

        if (isCorrectFlag) {
            Toast.makeText(
                this@MengucapkanHurufActivity,
                "BENAR! ${expected.uppercase()}",
                Toast.LENGTH_LONG
            ).show()
            isCorrect.value = true
            setRandomLetter()
        } else {
            // Logika baru untuk memberikan feedback yang lebih jelas
            val feedback = if (relevantWord.isBlank()) {
                "Tidak ada kata kunci terdeteksi. Coba ulangi pengucapan."
            } else {
                "Anda mengucapkan: '$relevantWord'. Harusnya: ${expected.uppercase()}."
            }
            Toast.makeText(
                this@MengucapkanHurufActivity,
                "Coba lagi. $feedback",
                Toast.LENGTH_SHORT
            ).show()
            isCorrect.value = false
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }
}

fun letterToDrawable(letter: String): Int {
    // Fungsi ini diambil dari kode asli Anda
    return when (letter.uppercase()) {
        "A" -> R.drawable.a
        "B" -> R.drawable.b
        "C" -> R.drawable.c
        "D" -> R.drawable.d
        "E" -> R.drawable.e
        "F" -> R.drawable.f
        "G" -> R.drawable.g
        "H" -> R.drawable.h
        "I" -> R.drawable.i
        "J" -> R.drawable.j
        "K" -> R.drawable.k
        "L" -> R.drawable.l
        "M" -> R.drawable.m
        "N" -> R.drawable.n
        "O" -> R.drawable.o
        "P" -> R.drawable.p
        "Q" -> R.drawable.q
        "R" -> R.drawable.r
        "S" -> R.drawable.s
        "T" -> R.drawable.t
        "U" -> R.drawable.u
        "V" -> R.drawable.v
        "W" -> R.drawable.w
        "X" -> R.drawable.x
        "Y" -> R.drawable.y
        "Z" -> R.drawable.z
        else -> R.drawable.a
    }
}