package com.kelompok6.smart_kids.ui.pages.writingWords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

// ====== COMPOSABLE UTAMA (STATELESS) ======
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingWordsScreen(
    onBackClick: () -> Unit,
    onOptionSelected: (String) -> Unit,
    onCheckAnswer: () -> Unit,
    wordImageResId: Int? = null,
    selectedWord: String? = null,
    isCorrect: Boolean? = null
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD8EEDC)
                )
            )
        },
        containerColor = Color(0xFFA5D6A7),
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                // === Konten Utama (di tengah layar) ===
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 40.dp,
                            start = 24.dp,
                            end = 24.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "MENULIS KATA",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (wordImageResId != null) {
                        androidx.compose.foundation.Image(
                            painter = painterResource(id = wordImageResId),
                            contentDescription = "Ilustrasi kata",
                            modifier = Modifier
                                .size(180.dp)
                                .background(Color.White, RoundedCornerShape(12.dp))
                                .padding(8.dp),
                            contentScale = androidx.compose.ui.layout.ContentScale.Fit
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .size(180.dp)
                                .background(Color.White, RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(id = R.drawable.ayam),
                                contentDescription = "Contoh huruf",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = androidx.compose.ui.layout.ContentScale.Fit
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Ayo susun hurufnya jadi \n" +
                                "kata yang benar!\n",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // === 5 TOMBOL PILIHAN KATA ===
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val options = listOf("K", "S", "A", "U", "D")
                        options.forEach { option ->
                            Button(
                                onClick = { onOptionSelected(option) },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedWord == option) {
                                        Color(0xFFE8E0D0)
                                    } else {
                                        Color(0xFFF8F5E8)
                                    },
                                    contentColor = Color.Black
                                ),
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp)
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                        }
                    }
                }

                // === FOOTER DI DALAM BOX UTAMA ===
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                        )
                        .align(Alignment.BottomCenter)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = 60.dp,
                                start = 24.dp,
                                end = 24.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        // ✅ TEKS PILIHAN DIPINDAH KE ATAS
                        if (selectedWord != null) {
                            Text(
                                text = "Kamu memilih: $selectedWord",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        } else {
                            Text(
                                text = "Pilih salah satu huruf di atas",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // ✅ DIVIDER DI BAWAH TEKS
                        Divider(
                            color = Color.Black.copy(alpha = 0.3f),
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        // === Tombol Cek Jawaban ===
                        Button(
                            onClick = onCheckAnswer,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFA5D6A7),
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .padding(horizontal = 32.dp)
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "CEK JAWABAN",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // === Feedback (opsional) ===
                        if (isCorrect != null) {
                            Text(
                                text = if (isCorrect) "Benar! 👏" else "Salah, coba lagi!",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (isCorrect) Color.Green else Color.Red,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    )
}

// ====== COMPOSABLE DENGAN STATE ======
@Composable
fun WritingWordsScreenContent(
    onBackClick: () -> Unit,
    correctAnswer: String = "KUCING",
    wordImageResId: Int? = null // Misal: R.drawable.cat
) {
    var selectedWord by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    WritingWordsScreen(
        onBackClick = onBackClick,
        onOptionSelected = { word ->
            selectedWord = word
            isCorrect = null
        },
        onCheckAnswer = {
            isCorrect = (selectedWord == correctAnswer)
        },
        wordImageResId = wordImageResId,
        selectedWord = selectedWord,
        isCorrect = isCorrect
    )
}

// ====== PREVIEW ======
@Preview(showBackground = true)
@Composable
fun PreviewWritingWordsScreen() {
    Smart_KidsTheme {
        WritingWordsScreenContent(onBackClick = {})
    }
}