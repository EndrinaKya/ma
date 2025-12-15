@file:OptIn(ExperimentalMaterial3Api::class)

package com.kelompok6.smart_kids.ui.pages.writingLetters

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import java.util.Locale

// =========================================================================
// RangkaiKataActivity: LOGIC (Activity)
// =========================================================================

class WritingLettersActivity : ComponentActivity() {

    // Map Huruf Awal ke Kata dan ID Drawable (Asumsi ID Drawable sudah tersedia)
    private val wordMap = mapOf(
        "A" to Pair("APEL", R.drawable.a),
        "B" to Pair("BOLA", R.drawable.baca),
        "C" to Pair("CICAK", R.drawable.c),
        "D" to Pair("DINDING", R.drawable.d),
        "E" to Pair("EMBER", R.drawable.e),
        "F" to Pair("FOTO", R.drawable.f),
        "G" to Pair("GAJAH", R.drawable.g),
        "H" to Pair("HARIMAU", R.drawable.h),
        "I" to Pair("IKAN", R.drawable.i),
        "J" to Pair("JENDELA", R.drawable.j),
        "K" to Pair("KUCING", R.drawable.k),
        "L" to Pair("LAMPU", R.drawable.l),
        "M" to Pair("MEJA", R.drawable.m),
        "N" to Pair("NAGA", R.drawable.n),
        "O" to Pair("OBAT", R.drawable.o),
        "P" to Pair("PENA", R.drawable.p),
        "Q" to Pair("QURAN", R.drawable.q),
        "R" to Pair("ROTI", R.drawable.r),
        "S" to Pair("SEPATU", R.drawable.s),
        "T" to Pair("TOPI", R.drawable.t),
        "U" to Pair("ULAR", R.drawable.u),
        "V" to Pair("VIDEO", R.drawable.v),
        "W" to Pair("WAJAN", R.drawable.w),
        "X" to Pair("XENON", R.drawable.x),
        "Y" to Pair("YOGA", R.drawable.y),
        "Z" to Pair("ZEBRA", R.drawable.z)
    ).mapValues { it.value.copy(first = it.value.first.uppercase(Locale.ROOT)) }
    private val alphabet = wordMap.keys.toList()

    // --- State Variables ---
    private val expectedLetter = mutableStateOf("A")
    private val expectedWord = mutableStateOf(wordMap["A"]!!.first)
    private val currentArrangedText = mutableStateOf("")
    private val hasResult = mutableStateOf(false)
    private val isCorrect = mutableStateOf(false)
    private val shuffledLetters = mutableStateOf(listOf<String>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRandomWordAndShuffleLetters()

        setContent {
            Smart_KidsTheme {
                RangkaiKataScreen(
                    expectedWord = expectedWord.value,
                    letterImageResId = wordMap[expectedLetter.value]?.second ?: R.drawable.a, // Fallback
                    arrangedText = currentArrangedText.value,
                    shuffledLetters = shuffledLetters.value,
                    hasResult = hasResult.value,
                    isCorrect = isCorrect.value,
                    onLetterClick = ::appendLetter,
                    onDeleteClick = ::deleteLastLetter,
                    onSubmitClick = ::checkResult,
                    onNextOrRetryClick = ::setRandomWordAndShuffleLetters,
                    onBackClick = { finish() }
                )
            }
        }
    }

    private fun setRandomWordAndShuffleLetters() {
        val randomLetter = alphabet.random()
        val randomPair = wordMap[randomLetter]!!

        expectedLetter.value = randomLetter
        expectedWord.value = randomPair.first
        currentArrangedText.value = ""
        hasResult.value = false
        isCorrect.value = false

        val correctLetters = randomPair.first.map { it.toString() }

        // Tambahkan huruf pengecoh untuk membuat total 8 tombol jika kata pendek
        val targetSize = 8
        val decoyCount = targetSize - correctLetters.size

        val availableAlphabet = alphabet.filter { it !in correctLetters }
        val decoys = if (availableAlphabet.isNotEmpty() && decoyCount > 0) {
            availableAlphabet.shuffled().take(decoyCount)
        } else {
            emptyList()
        }

        shuffledLetters.value = (correctLetters + decoys).distinct().shuffled()
    }

    private fun appendLetter(letter: String) {
        if (!isCorrect.value) { // Hanya bisa merangkai jika belum BENAR
            currentArrangedText.value += letter
            hasResult.value = false
        }
    }

    private fun deleteLastLetter() {
        if (currentArrangedText.value.isNotEmpty() && !isCorrect.value) {
            currentArrangedText.value = currentArrangedText.value.dropLast(1)
            hasResult.value = false
            isCorrect.value = false
        }
    }

    private fun checkResult() {
        if (currentArrangedText.value.isEmpty()) return

        hasResult.value = true

        val arranged = currentArrangedText.value.uppercase(Locale.ROOT)
        val expected = expectedWord.value.uppercase(Locale.ROOT)
        val context = this

        if (arranged == expected) {
            Toast.makeText(
                context,
                "BENAR! Kata yang kamu rangkai adalah: ${expectedWord.value}",
                Toast.LENGTH_LONG
            ).show()
            isCorrect.value = true
        } else {
            Toast.makeText(
                context,
                "SALAH. Susunan kata kamu: ${currentArrangedText.value}",
                Toast.LENGTH_SHORT
            ).show()
            isCorrect.value = false
        }
    }
}

// =========================================================================
// RangkaiKataScreen: UI (Composable)
// =========================================================================

@Composable
fun RangkaiKataScreen(
    expectedWord: String,
    letterImageResId: Int,
    arrangedText: String,
    shuffledLetters: List<String>,
    hasResult: Boolean,
    isCorrect: Boolean,
    onLetterClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onSubmitClick: () -> Unit,
    onNextOrRetryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Rangkai Kata") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Gambar & Petunjuk
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Image(
                    painter = painterResource(id = letterImageResId),
                    contentDescription = "Gambar untuk kata: $expectedWord",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Rangkailah kata yang sesuai dengan gambar di atas:",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // 2. Tampilan Teks Rangkaian
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (arrangedText.isEmpty()) {
                    Text(
                        text = "Rangkai kata di sini...",
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
                        fontSize = 18.sp
                    )
                } else {
                    // Tampilkan setiap huruf dengan spasi
                    Text(
                        text = arrangedText.chunked(1).joinToString(" "),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Tombol Aksi (Hapus & Submit) / Hasil
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (hasResult && !isCorrect) {
                    // Tampilkan Ulangi jika Salah
                    Button(
                        onClick = {
                            onNextOrRetryClick()
                            Toast.makeText(context, "Mulai dengan kata baru!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)), // Merah
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("ULANGI & KATA BARU", color = Color.White)
                    }
                } else if (isCorrect) {
                    // Tampilkan Next jika Benar
                    Button(
                        onClick = {
                            onNextOrRetryClick()
                            Toast.makeText(context, "Selamat! Lanjut ke kata berikutnya.", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Hijau
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("LANJUT (NEXT)", color = Color.White)
                    }
                } else {
                    // Tampilkan Hapus dan Submit saat bermain
                    OutlinedButton(
                        onClick = onDeleteClick,
                        enabled = arrangedText.isNotEmpty(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("HAPUS")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = onSubmitClick,
                        enabled = arrangedText.isNotEmpty(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("SUBMIT")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 4. Grid Tombol Huruf (Hanya tampilkan jika belum BENAR)
            if (!isCorrect) {
                Text(
                    text = "Klik huruf untuk merangkai kata:",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(shuffledLetters) { letter ->
                        Button(
                            onClick = { onLetterClick(letter) },
                            modifier = Modifier.aspectRatio(1f), // Agar tombol selalu berbentuk kotak
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(letter, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                }
            }
        }
    }
}