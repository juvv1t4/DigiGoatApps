package com.example.digigoat_login

import BottomNavbar
import CatatanPakan
import DataRecoveryScreen
import EditProfileScreen
import HealthRecordScreen
import NewsScreen
import NotificationScreen
import TambahCatatan
import TopBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.digigoat_login.navigation.Screen

@Composable
fun Digigoat() {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // Title mapping
    val routeTitles = mapOf(
        Screen.Dashboard.route to "DigiGoat",
        Screen.Notification.route to "Notifikasi",
        Screen.Management.route to "Management",
        Screen.CatatanPerkembangan.route to "Catatan Perkembangan",
        Screen.TambahCatatan.route to "Tambah Catatan",
        Screen.NewsScreen.route to "Berita Terkini",
        Screen.RegisterScreen.route to "Register",
        Screen.ProfileScreen.route to "Profile",
        Screen.HealthRecordScreen.route to "Catatan Kesehatan",
        Screen.DataRecoveryScreen.route to "Pemulihan Data",
        Screen.EditProfileScreen.route to "Edit Profile",
        Screen.CatatanPakan.route to "Catatan Pakan"// New screen route added
    )



    val topBarTitle = routeTitles[currentRoute] ?: "DigiGoat"

    // Top Bar Visibility Logic
    val hideTopBarRoutes = listOf(Screen.Login.route, Screen.RegisterScreen.route)

    Scaffold(
        topBar = {
            if (currentRoute !in hideTopBarRoutes) {
                TopBar(
                    title = topBarTitle,
                    navController = navController,
                    currentRoute = currentRoute ?: "",
                    onNotificationClick = {
                        navController.navigate(Screen.Notification.route)
                    }
                )
            }
        },
        bottomBar = {
            if (shouldShowBottomBar(currentRoute)) {
                BottomNavbar(navController = navController)
            }
        }
    ) { innerPadding -> // This lambda receives innerPadding as a parameter
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding) // Correctly passing innerPadding
        ) {
            composable(Screen.Login.route) { LoginScreen(navController) }
            composable(Screen.Dashboard.route) { DashboardScreen(navController) }
            composable(Screen.Notification.route) { NotificationScreen() }
            composable(Screen.Management.route) { ManagementScreen(navController) }
            composable(Screen.CatatanPerkembangan.route) { CatatanPerkembangan(navController)}
            composable(Screen.TambahCatatan.route) { TambahCatatan() }
            composable(Screen.NewsScreen.route) { NewsScreen(navController) }
            composable(Screen.RegisterScreen.route) { RegisterScreen(navController) }
            composable(Screen.ProfileScreen.route) { ProfileScreen(navController) }
            composable (Screen.HealthRecordScreen.route) { HealthRecordScreen() }
            composable(Screen.DataRecoveryScreen.route) {DataRecoveryScreen(navController)}
            composable(Screen.EditProfileScreen.route) {EditProfileScreen(navController)}
            composable(Screen.CatatanPakan.route) {CatatanPakan(navController)}
        }
    }
}

@Composable
fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return currentRoute in listOf(
        Screen.Dashboard.route,
        Screen.Management.route,
        Screen.NewsScreen.route,
        Screen.ProfileScreen.route
    )
}
