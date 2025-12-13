@file:OptIn(ExperimentalMaterial3Api::class)

package com.kelompok6.smart_kids.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.kelompok6.smart_kids.ui.pages.components.SlideBar
import com.kelompok6.smart_kids.ui.theme.Smart_KidsTheme
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onMenuItemClick: (String) -> Unit = {}
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SlideBar(onMenuClick = onMenuItemClick)
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.close() }
                                },
                                modifier = Modifier.padding(start = 20.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.menu),
                                    contentDescription = "Menu",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
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
                        .padding(horizontal = 24.dp), // ✅ konsisten dengan drawer
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
                            .aspectRatio(1.8f)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Smart_KidsTheme {
        HomeScreen {}
    }
}