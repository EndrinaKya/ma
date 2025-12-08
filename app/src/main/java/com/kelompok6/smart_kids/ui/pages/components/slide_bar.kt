// com.kelompok6.smart_kids.ui.components.Sidebar.kt

package com.kelompok6.smart_kids.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok6.smart_kids.R
import com.kelompok6.smart_kids.ui.pages.home.HomeScreen
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme

@Composable
fun Sidebar(
    onMenuSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .background(Color(0xFFE8F5E9))
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Profil
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profil),
                contentDescription = "Profil",
                tint = Color.Gray,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("Nama Anak", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Level 5", fontSize = 14.sp, color = Color.Gray)
            }
        }

        // Menu
        MenuItem(text = "Beranda") { onMenuSelected("home") }
        Spacer(modifier = Modifier.height(8.dp))
        MenuItem(text = "Membaca") { onMenuSelected("membaca") }
        Spacer(modifier = Modifier.height(8.dp))
        MenuItem(text = "Menulis") { onMenuSelected("menulis") }
        Spacer(modifier = Modifier.height(8.dp))
        MenuItem(text = "Edit Profil") { onMenuSelected("profile") }

        Spacer(modifier = Modifier.height(24.dp)) // jarak ke tombol tutup

        // Tutup
        Text(
            text = "Tutup",
            color = Color(0xFF66BB6A),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onDismiss() }
                .padding(6.dp)
        )
    }
}
@Composable
private fun MenuItem(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

// ✅ Preview
@Preview(showBackground = true)
@Composable
fun SidebarPreview() {
    Smart_KidsTheme {
        Sidebar(
            onMenuSelected = {},
            onDismiss = {}
        )
    }
}