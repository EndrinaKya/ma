package com.kelompok6.smart_kids.ui.pages.membaca

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

@Composable
fun ReadingScreen(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC8E6C9)) // hijau muda
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // === 1. Panah kembali (kiri atas) ===
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Kembali",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // === 2. Judul "MEMBACA" dengan ikon ===
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.baca),
                    contentDescription = "Buku",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "MEMBACA",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // === 3. Kalimat motivasi ===
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "“Yuk mulai membaca! Setiap cerita bisa membuka dunia baru yang penuh hal seru untuk kamu jelajahi!”",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            // === 4. Bagian HURUF ===
            Spacer(modifier = Modifier.height(60.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 24.dp)
                    .background(Color(0xFFF1F8E9), RoundedCornerShape(25.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "HURUF",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Mengucapkan Huruf",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* mulai huruf */ },
                modifier = Modifier
                    .height(40.dp)
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF81C784)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("MULAI", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            // === 5. Bagian KATA ===
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 24.dp)
                    .background(Color(0xFFF1F8E9), RoundedCornerShape(25.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "KATA",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Memahami Kata",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* mulai kata */ },
                modifier = Modifier
                    .height(40.dp)
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF81C784)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("MULAI", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewReadingScreen() {
    Smart_KidsTheme {
        ReadingScreen(onBackClick = {})
    }
}