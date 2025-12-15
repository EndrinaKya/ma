package com.kelompok6.smart_kids.ui.pages.memahami

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
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import java.util.Locale

class MemahamiKataActivity : ComponentActivity() {

    // Map Huruf ke Kata dan ID Drawable (Asumsi ID Drawable sudah tersedia)
    private val wordMap = mapOf(
        "A" to Pair("Apel", R.drawable.a),
        "B" to Pair("Bola", R.drawable.baca),
        "C" to Pair("Cicak", R.drawable.c),
        "D" to Pair("Dinding", R.drawable.d),
        "E" to Pair("Ember", R.drawable.e),
        "F" to Pair("Foto", R.drawable.f),
        "G" to Pair("Gajah", R.drawable.g),
        "H" to Pair("Harimau", R.drawable.h),
        "I" to Pair("Ikan", R.drawable.i),
        "J" to Pair("Jendela", R.drawable.j),
        "K" to Pair("Kucing", R.drawable.k),
        "L" to Pair("Lampu", R.drawable.l),
        "M" to Pair("Meja", R.drawable.m),
        "N" to Pair("Naga", R.drawable.n),
        "O" to Pair("Obat", R.drawable.o),
        "P" to Pair("Pena", R.drawable.p),
        "Q" to Pair("Quran", R.drawable.q),
        "R" to Pair("Roti", R.drawable.r),
        "S" to Pair("Sepatu", R.drawable.s),
        "T" to Pair("Topi", R.drawable.t),
        "U" to Pair("Ular", R.drawable.u),
        "V" to Pair("Video", R.drawable.v),
        "W" to Pair("Wajan", R.drawable.w),
        "X" to Pair("Xenon", R.drawable.x),
        "Y" to Pair("Yoga", R.drawable.y),
        "Z" to Pair("Zebra", R.drawable.z)
    )
    private val alphabet = wordMap.keys.toList()

    // --- State Variables ---
    private val isListening = mutableStateOf(false)
    private val recognizedText = mutableStateOf("")
    private val expectedLetter = mutableStateOf("A")
    private val expectedWord = mutableStateOf(wordMap["A"]!!.first)
    private val hasResult = mutableStateOf(false)
    private val isCorrect = mutableStateOf(false)

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (!granted) {
                Toast.makeText(
                    this@MemahamiKataActivity,
                    "Izin mikrofon diperlukan",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRandomWord()
        checkPermission()
        setupSpeechRecognizer()

        setContent {
            Smart_KidsTheme {
                MemahamiKataScreen(
                    expectedWord = expectedWord.value,
                    letterImageResId = wordMap[expectedLetter.value]?.second,
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
                                    this@MemahamiKataActivity,
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
                    onRetryClick = {
                        // Dipakai untuk 'Next' saat benar atau 'Ulangi' saat salah
                        // Logika: Jika benar, reset dan ambil kata baru (onRetryClick dipanggil dari tombol NEXT)
                        // Jika salah, reset dan ambil kata yang sama (otomatis karena setRandomWord dipanggil, tapi startListening dipanggil di bawah)
                        setRandomWord()

                        // Setelah reset, cek permission lagi jika tombol mic ditekan
                        if (ContextCompat.checkSelfPermission(
                                this@MemahamiKataActivity,
                                Manifest.permission.RECORD_AUDIO
                            ) == PackageManager.PERMISSION_GRANTED && !isListening.value
                        ) {
                            // Cek tambahan: start listening hanya jika hasil sebelumnya SALAH (ingin ulangi)
                            // Jika onRetryClick dipanggil dari tombol NEXT (isCorrect true sebelumnya), biarkan user menekan mic lagi
                            // Namun, karena setRandomWord mereset isCorrect, kita asumsikan jika dipanggil berarti ingin reset/mulai baru.
                        }
                    }
                )
            }
        }
    }

    private fun setRandomWord() {
        val randomLetter = alphabet.random()
        val randomPair = wordMap[randomLetter]!!

        expectedLetter.value = randomLetter
        expectedWord.value = randomPair.first
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

                // Langsung proses hasil STT
                if (!text.isNullOrBlank()) {
                    checkResult(text)
                }
            }

            override fun onError(error: Int) {
                isListening.value = false
                recognizedText.value = ""
                hasResult.value = false
                isCorrect.value = false
                Log.e("STT_MEMAHAMI", "Error $error")
                Toast.makeText(
                    this@MemahamiKataActivity,
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

    // FUNGSI CHECKRESULT YANG HANYA MENERIMA KATA KUNCI TUNGGAL
    private fun checkResult(text: String) {
        val expected = expectedWord.value.lowercase(Locale.ROOT)
        var result = text.lowercase(Locale.ROOT)

        // Normalisasi: hapus spasi berlebih, tanda baca
        result = result.replace(Regex("[^a-zA-Z\\s]"), "").trim()

        // 1. Dapatkan KATA KUNCI yang diharapkan
        val correctKeywords = mutableSetOf<String>()
        correctKeywords.add(expected)

        // --- Tambahkan variasi pengucapan yang mungkin salah didengar STT ---
        when (expected) {
            "apel" -> {
                correctKeywords.add("aple")
                correctKeywords.add("apple")
            }
            "bola" -> {
                correctKeywords.add("pola")
                correctKeywords.add("buola")
            }
            "ikan" -> {
                correctKeywords.add("ikhan")
            }
            "roti" -> {
                correctKeywords.add("ruti")
            }
            // Tambahkan kasus lain sesuai kebutuhan
        }
        // --------------------------------------------------------------------

        // 2. Ekstrak KATA RELEVAN (Kata terakhir dari hasil STT)
        // Ini mengabaikan kata pengantar seperti "sebuah", "itu", dll.
        val resultWords = result.split(Regex("\\s+")).filter { it.isNotBlank() }
        val lastWord = resultWords.lastOrNull() ?: ""

        // 3. Pengecekan: Apakah kata kunci yang diucapkan ada di daftar yang benar?
        val finalCheck = correctKeywords.contains(lastWord)

        if (finalCheck) {
            Toast.makeText(
                this@MemahamiKataActivity,
                "BENAR! ${expectedWord.value}",
                Toast.LENGTH_LONG
            ).show()
            isCorrect.value = true
        } else {
            Toast.makeText(
                this@MemahamiKataActivity,
                "Coba lagi. Harusnya: ${expectedWord.value}",
                Toast.LENGTH_SHORT
            ).show()
            isCorrect.value = false
            hasResult.value = true // Biarkan UI menampilkan tombol ulangi
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