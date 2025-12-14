//package com.kelompok6.smart_kids.ui
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import com.kelompok6.smart_kids.ui.pages.components.SlideBar
//import com.kelompok6.smart_kids.ui.pages.home.HomeScreen
//import kotlinx.coroutines.launch
//
//@Composable
//fun SmartKidsApp() {
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//    var currentScreen by remember { mutableStateOf("home") }
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            SlideBar { route ->
//                currentScreen = route
//                scope.launch { drawerState.close() }
//            }
//        },
//        content = {
//            when (currentScreen) {
//                "home" -> HomeScreen(onMenuClick = {
//                    scope.launch { drawerState.open() }
//                })
//                "reading" -> HomeScreen(onMenuClick = { scope.launch { drawerState.open() } }) // placeholder
//                "writing" -> HomeScreen(onMenuClick = { scope.launch { drawerState.open() } }) // placeholder
//                "edit_profile" -> HomeScreen(onMenuClick = { scope.launch { drawerState.open() } }) // placeholder
//                else -> HomeScreen(onMenuClick = { scope.launch { drawerState.open() } })
//            }
//        }
//    )
//}