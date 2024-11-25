package com.example.digigoat_login.navigation

sealed class Screen(val route: String) {
    object Login : Screen(route = "Login")
    object RegisterScreen : Screen(route = "Register")  // Fixed typo: 'Regiter' -> 'Register'
    object Dashboard : Screen(route = "Dashboard")
    object Notification : Screen(route = "Notification")
    object Management : Screen(route = "management")
    object CatatanPerkembangan: Screen(route = "CatatanPerkembangan")
    object CatatanPakan: Screen(route = "CatatanPakan")
    object HealthRecordScreen: Screen(route = "CatatanKesehatan")
    object TambahCatatan: Screen(route = "TambahCatatan")
    object NewsScreen: Screen(route = "NewsScreen")
    object ProfileScreen: Screen(route = "ProfileScreen")
    object DataRecoveryScreen: Screen(route = "Backup")
    object EditProfileScreen: Screen(route = "EditProfileScreen")
}
