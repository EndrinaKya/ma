package com.kelompok6.smart_kids.ui.pages.mengucapkan

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
fun MengucapkanHurufScreen(
    expectedLetter: String,
    letterImageResId: Int,
    isListening: Boolean,
    previewText: String,
    hasResult: Boolean,
    isCorrect: Boolean,
    onBackClick: () -> Unit,
    onMicClick: () -> Unit,
    onSubmitClick: () -> Unit, // Parameter ini tidak digunakan dalam desain ini
    onRetryClick: () -> Unit
) {
    // Warna-warna yang digunakan dalam desain
    val primaryColor = Color(0xFF5A7B5F) // Contoh warna hijau gelap yang cocok dengan tema
    val backgroundColor = Color(0xFFD8EEDC) // Warna latar belakang TopBar & Scaffold
    val correctColor = Color(0xFF66BB6A) // Hijau untuk Benar
    val whiteColor = Color.White
    val lightGrayColor = Color(0xFFF5F5F5)

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
                        text = "MENGUCAPKAN HURUF",
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
        containerColor = backgroundColor
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {

            // --- BAGIAN ATAS (Gambar Huruf & Instruksi) ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .padding(bottom = 250.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(id = letterImageResId),
                    contentDescription = "Huruf $expectedLetter",
                    modifier = Modifier
                        .size(180.dp)
                        .background(whiteColor, RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Sebut hurufnya yuk!",
                    fontSize = 16.sp,
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
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            // --- FOOTER (Aksi) ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        color = whiteColor,
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isCorrect) {
                        // 1. ✅ BENAR → TAMPILKAN TOMBOL NEXT
                        Button(
                            onClick = onRetryClick, // Pindah ke huruf berikutnya (setRandomLetter)
                            modifier = Modifier
                                .padding(horizontal = 48.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = correctColor,
                                contentColor = whiteColor
                            )
                        ) {
                            Text("NEXT", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    } else if (hasResult) {
                        // 2. ❌ SALAH (atau Tunggu Proses) → TAMPILKAN PESAN SALAH + TOMBOL ULANGI
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        ) {
                            Text(
                                text = "❌",
                                fontSize = 24.sp,
                                color = Color.Red
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Ups, jawaban salah.\nAyo ulangi lagi!",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(Modifier.height(24.dp))

                        // Tombol ulangi (gunakan emoji 🔄)
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(lightGrayColor)
                                .clickable { onRetryClick() }, // Ulangi rekaman (reset state)
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

// ... (Preview Composables tetap sama)