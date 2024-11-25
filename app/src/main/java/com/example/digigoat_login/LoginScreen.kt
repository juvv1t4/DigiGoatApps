package com.example.digigoat_login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.digigoat_login.navigation.Screen

@Composable
fun LoginScreen(navController: NavHostController) {

    var nama by remember { mutableStateOf("") }
    var sandi by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Scrollable Column to handle keyboard overlap
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Make it scrollable
            .imePadding(), // Adjust layout when keyboard appears
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Image
        Image(
            painter = painterResource(R.drawable.k_login), // Image as header
            contentDescription = "Login Image",
            modifier = Modifier.size(width = 412.dp, height = 291.dp)
        )

        Spacer(modifier = Modifier.height(19.dp))
        Text(text = "Masuk", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))
        FormInput(
            label = "Nama Pengguna",
            value = nama,
            onValueChange = { nama = it }
        )

        Spacer(modifier = Modifier.height(10.dp))
        FormInput(
            label = "Kata Sandi",
            value = sandi,
            onValueChange = { sandi = it },
            isPassword = true
        )

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Lupa kata sandi?", fontSize = 13.sp, modifier = Modifier.clickable { })

        Spacer(modifier = Modifier.height(19.dp))
        Button(
            onClick = {
                if (nama.isEmpty() || sandi.isEmpty()) {
                    errorMessage = "Nama Pengguna atau Kata Sandi tidak boleh kosong"
                } else {
                    Log.i("Credential", "Nama : $nama Sandi : $sandi")
                    navController.navigate(Screen.Dashboard.route)
                }
            },
            modifier = Modifier.size(width = 268.dp, height = 49.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF976B0E))
        ) {
            Text(text = "Masuk", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(19.dp))
        Text(text = "Atau")

        Spacer(modifier = Modifier.height(19.dp))
        Image(
            painter = painterResource(R.drawable.google), // Google image
            contentDescription = "Google",
            modifier = Modifier.size(width = 37.dp, height = 37.dp).clickable { }
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Belum memiliki akun? Daftar",
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                navController.navigate(Screen.RegisterScreen.route)
            }
        )
    }
}

@Composable
fun RegisterScreen(navController: NavHostController) {

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var sandi by remember { mutableStateOf("") }
    var konfirmasiSandi by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Scrollable Column to handle keyboard overlap
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Make it scrollable
            .imePadding(), // Adjust layout when keyboard appears
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Image
        Image(
            painter = painterResource(R.drawable.k_login), // Image as header
            contentDescription = "Register Image",
            modifier = Modifier.size(width = 412.dp, height = 291.dp)
        )

        Spacer(modifier = Modifier.height(19.dp))
        Text(text = "Daftar", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))
        FormInput(
            label = "Nama Pengguna",
            value = nama,
            onValueChange = { nama = it }
        )

        Spacer(modifier = Modifier.height(10.dp))
        FormInput(
            label = "Email",
            value = email,
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.height(10.dp))
        FormInput(
            label = "Kata Sandi",
            value = sandi,
            onValueChange = { sandi = it },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(10.dp))
        FormInput(
            label = "Konfirmasi Kata Sandi",
            value = konfirmasiSandi,
            onValueChange = { konfirmasiSandi = it },
            isPassword = true
        )

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(19.dp))
        Button(
            onClick = {
                if (nama.isEmpty() || email.isEmpty() || sandi.isEmpty() || konfirmasiSandi.isEmpty()) {
                    errorMessage = "Semua kolom harus diisi"
                } else if (sandi != konfirmasiSandi) {
                    errorMessage = "Kata sandi dan konfirmasi kata sandi tidak cocok"
                } else {
                    Log.i("Register", "Nama: $nama, Email: $email, Sandi: $sandi")
                    navController.navigate(Screen.Dashboard.route)
                }
            },
            modifier = Modifier.size(width = 268.dp, height = 49.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF976B0E))
        ) {
            Text(text = "Daftar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Sudah memiliki akun? Masuk",
            fontSize = 16.sp,
            modifier = Modifier.clickable { navController.navigate(Screen.Login.route) }
        )
    }
}

@Composable
fun FormInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = Modifier.size(width = 375.dp, height = 60.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}
