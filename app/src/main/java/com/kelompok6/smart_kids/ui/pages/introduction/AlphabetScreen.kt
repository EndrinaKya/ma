@file:kotlin.OptIn(ExperimentalMaterial3Api::class)

package com.kelompok6.smart_kids.ui.pages.introduction

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.data.response.AlphabetItem
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.annotation.OptIn
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun AlphabetScreen(
    alphabetList: List<AlphabetItem>,  // ✅ Terima data dari API
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onLetterClick: (String) -> Unit  // ✅ Kirim huruf yang diklik
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.padding(start = 20.dp)
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
        containerColor = Color(0xFFA5D6A7)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // === Instruksi + Speaker ===
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.speaker),
                    contentDescription = "Speaker",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Yuk, tekan hurufnya dan dengar bunyi-nya!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // ✅ GRID DINAMIS (tidak ada hardcode!)
            AlphabetGrid(
                alphabetList = alphabetList,
                onLetterClick = onLetterClick
            )

            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF81C784)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "NEXT",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

// ✅ Komponen grid dinamis
@Composable
fun AlphabetGrid(
    alphabetList: List<AlphabetItem>,
    onLetterClick: (String) -> Unit
) {
    // Kelompokkan jadi baris berisi maks 5 huruf (seperti desain asli)
    val rows = alphabetList.chunked(5)

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (row in rows) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                for (item in row) {
                    AlphabetButton(letter = item.letter) {
                        onLetterClick(item.letter)
                    }
                }
            }
        }
    }
}

@Composable
fun AlphabetButton(letter: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = letter,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

// ✅ Preview dengan data dummy
@Preview(showBackground = true)
@Composable
fun PreviewAlphabetScreen() {
    val dummyList = listOf(
        AlphabetItem("A", ""),
        AlphabetItem("B", ""),
        AlphabetItem("C", ""),
        AlphabetItem("D", ""),
        AlphabetItem("E", ""),
        AlphabetItem("F", "")
    )
    Smart_KidsTheme {
        AlphabetScreen(
            alphabetList = dummyList,
            onBackClick = {},
            onNextClick = {},
            onLetterClick = {}
        )
    }
}