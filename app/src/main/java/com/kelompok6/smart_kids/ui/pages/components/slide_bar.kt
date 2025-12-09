package com.kelompok6.smart_kids.ui.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R

@Composable
fun SlideBar(onMenuClick: (String) -> Unit) {
    ModalDrawerSheet(
        modifier = Modifier
            .background(Color(0xFFD8EEDC))
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .width(300.dp) // ← Atur lebar di sini
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header: Back + Profile
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /* ... */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.profil),
                        contentDescription = "Profile",
                        tint = Color.Black,
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Menu Items
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DrawerItem(
                        text = "Beranda",
                        onClick = { onMenuClick("home") },
                        backgroundColor = Color.White
                    )
                    DrawerItem(
                        text = "Membaca",
                        onClick = { onMenuClick("reading") },
                        backgroundColor = Color(0xFF9CD7A0)
                    )
                    DrawerItem(
                        text = "Menulis",
                        onClick = { onMenuClick("writing") },
                        backgroundColor = Color.White
                    )
                    DrawerItem(
                        text = "Edit Profile",
                        onClick = { onMenuClick("edit_profile") },
                        backgroundColor = Color(0xFF9CD7A0)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(100.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = "KIDS.",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
private fun DrawerItem(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    NavigationDrawerItem(
        label = { Text(text, fontSize = 16.sp, color = Color.Black) },
        selected = false,
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
            .background(backgroundColor)
            .padding(vertical = 12.dp, horizontal = 24.dp)
    )
}

