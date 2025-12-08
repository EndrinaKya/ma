@file:OptIn(ExperimentalMaterial3Api::class)

package com.kelompok6.smart_kids.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

@Composable
fun HomeScreen(
    onMenuClick: () -> Unit = {}
) {

    // Gunakan Surface supaya mengikuti Material Theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFA5D6A7)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🧭 TOP BAR
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "Menu",
                            tint = Color.Black
                        )
                    }
                },
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFD8EEDC)
                )
            )

            // 📌 Konten Utama
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Hai Anak Hebat,\nYuk Belajar di SMART KIDS.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    painter = painterResource(id = R.drawable.beranda),
                    contentDescription = "Education Illustration",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.8f) // lebih stabil di berbagai layar
                )
            }
        }
    }
}


@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun HomeScreenPreview() {
    Smart_KidsTheme {
        HomeScreen()
    }
}
