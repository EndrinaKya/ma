package com.kelompok6.smart_kids.ui.pages.memahami

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemahamiKataScreen(
    expectedWord: String,
    letterImageResId: Int?,
    isListening: Boolean,
    previewText: String,
    hasResult: Boolean,
    isCorrect: Boolean,
    onBackClick: () -> Unit,
    onMicClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    val backgroundColor = Color(0xFFD8EEDC)
    val containerColor = Color(0xFFA5D6A7)
    val footerColor = Color(0xFFE8F5E9)
    val correctColor = Color(0xFF66BB6A)
    val whiteColor = Color.White
    val lightGrayColor = Color(0xFFF5F5F5)
    val primaryColor = Color(0xFF5A7B5F) // Warna utama icon mic

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back), // Asumsi R.drawable.back ada
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                title = {
                    Text(
                        text = "MEMAHAMI KATA",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor
                )
            )
        },
        containerColor = containerColor,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                // --- Konten Utama (Gambar & Instruksi) ---
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .padding(bottom = 250.dp + 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    // Teks untuk huruf awal kata
                    Text(
                        text = "Diawali Huruf: ${expectedWord.first().uppercaseChar()}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    // Gambar Kata
                    if (letterImageResId != null) {
                        Image(
                            painter = painterResource(id = letterImageResId),
                            contentDescription = "Gambar untuk kata $expectedWord",
                            modifier = Modifier
                                .size(180.dp)
                                .background(whiteColor, RoundedCornerShape(12.dp))
                                .padding(8.dp),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(180.dp)
                                .background(whiteColor, RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Gambar\n${expectedWord.uppercase()}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Instruksi
                    Text(
                        text = "Ayo, ucapkan \nnama gambar di atas!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tampilkan hasil suara jika ada
                    if (hasResult && previewText.isNotBlank()) {
                        Text(
                            text = "Terdengar: \"$previewText\"",
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                    }
                }

                // === Footer (Aksi) ===
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    Divider(
                        color = Color.Black.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .background(
                                color = footerColor,
                                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (isCorrect) {
                                // 1. ✅ BENAR → TAMPILKAN TOMBOL NEXT
                                Text("BENAR!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = correctColor)
                                Spacer(Modifier.height(24.dp))
                                Button(
                                    onClick = onRetryClick, // Pindah ke kata berikutnya
                                    modifier = Modifier
                                        .padding(horizontal = 48.dp)
                                        .height(48.dp),
                                    shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = correctColor,
                                        contentColor = whiteColor
                                    )
                                ) {
                                    Text("LANJUT", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                }
                            } else if (hasResult) {
                                // 2. ❌ SALAH (atau Tunggu Proses) → PESAN SALAH + TOMBOL ULANGI
                                Text(
                                    text = "❌ Jawaban salah. Harusnya: ${expectedWord.uppercase()}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Red,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(Modifier.height(24.dp))

                                // Tombol ulangi
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(lightGrayColor)
                                        .clickable { onRetryClick() }, // Ulangi rekaman
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "🔄",
                                        fontSize = 24.sp,
                                        color = Color.Black
                                    )
                                }
                            } else {
                                // 3. 🎤 BELUM REKAM / IDLE → TAMPILKAN MIC
                                IconButton(
                                    onClick = onMicClick,
                                    modifier = Modifier.size(60.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.mic), // Asumsi R.drawable.mic ada
                                        contentDescription = "Tekan untuk berbicara",
                                        tint = primaryColor,
                                        modifier = Modifier.size(40.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = if (isListening) "Mendengarkan..." else "Tekan untuk berbicara",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMemahamiKataScreenIdle() {
    Smart_KidsTheme {
        MemahamiKataScreen(
            expectedWord = "Apel",
            letterImageResId = R.drawable.a, // Asumsi R.drawable.a ada
            isListening = false,
            previewText = "",
            hasResult = false,
            isCorrect = false,
            onBackClick = {},
            onMicClick = {},
            onRetryClick = {}
        )
    }
}