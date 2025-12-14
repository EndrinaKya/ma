package com.kelompok6.smart_kids.ui.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text



@Composable
fun SlideBar(onMenuClick: (String) -> Unit) {


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color(0xFFD8EEDC))
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Header: Back + Profil
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            IconButton(
                onClick = {
                    // Tidak perlu tutup manual — item menu akan tutup otomatis
                    // Tapi jika ingin tutup saat klik "back", tambahkan logika di HomeScreen
                },
                modifier = Modifier.size(48.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profil),
                    contentDescription = "Profil",
                    tint = Color.Black,
                    modifier = Modifier.size(64.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Menu Items
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DrawerItem(
                text = "Beranda",
                onClick = { onMenuClick("home") },
                backgroundColor = Color(0xFFF8F5E8)
            )
            DrawerItem(
                text = "Membaca",
                onClick = { onMenuClick("reading") },
                backgroundColor = Color(0xFF9CD7A0)
            )
            DrawerItem(
                text = "Menulis",
                onClick = { onMenuClick("writing") },
                backgroundColor = Color(0xFFF8F5E8)
            )
            DrawerItem(
                text = "Edit Profile",
                onClick = { onMenuClick("edit_profile") },
                backgroundColor = Color(0xFF9CD7A0)
            )
        }
    }
}

@Composable
private fun DrawerItem(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSlideBar() {
    Smart_KidsTheme {
        SlideBar(onMenuClick = {})
    }
}