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
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlphabetScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
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
                            modifier = Modifier.size(40.dp)
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

            // === Grid Huruf A-Z (Manual seperti awal) ===
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Baris 1: A-E
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AlphabetButton("A") { /* suara A */ }
                    AlphabetButton("B") { /* suara B */ }
                    AlphabetButton("C") { /* suara C */ }
                    AlphabetButton("D") { /* suara D */ }
                    AlphabetButton("E") { /* suara E */ }
                }

                // Baris 2: F-J
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AlphabetButton("F") { /* suara F */ }
                    AlphabetButton("G") { /* suara G */ }
                    AlphabetButton("H") { /* suara H */ }
                    AlphabetButton("I") { /* suara I */ }
                    AlphabetButton("J") { /* suara J */ }
                }

                // Baris 3: K-O
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AlphabetButton("K") { /* suara K */ }
                    AlphabetButton("L") { /* suara L */ }
                    AlphabetButton("M") { /* suara M */ }
                    AlphabetButton("N") { /* suara N */ }
                    AlphabetButton("O") { /* suara O */ }
                }

                // Baris 4: P-T
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AlphabetButton("P") { /* suara P */ }
                    AlphabetButton("Q") { /* suara Q */ }
                    AlphabetButton("R") { /* suara R */ }
                    AlphabetButton("S") { /* suara S */ }
                    AlphabetButton("T") { /* suara T */ }
                }

                // Baris 5: U-X
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AlphabetButton("U") { /* suara U */ }
                    AlphabetButton("V") { /* suara V */ }
                    AlphabetButton("W") { /* suara W */ }
                    AlphabetButton("X") { /* suara X */ }
                }

                // Baris 6: Y-Z
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AlphabetButton("Y") { /* suara Y */ }
                    AlphabetButton("Z") { /* suara Z */ }
                }
            }

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
                        color = Color.White
                    )
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
            .aspectRatio(1f), // ✅ Ini kunci agar tidak miring!
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

@Preview(showBackground = true)
@Composable
fun PreviewAlphabetScreen() {
    Smart_KidsTheme {
        AlphabetScreen(onBackClick = {}, onNextClick = {})
    }
}