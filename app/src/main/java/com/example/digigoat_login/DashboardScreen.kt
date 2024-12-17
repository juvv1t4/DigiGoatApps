package com.example.digigoat_login

import ProtectionMeter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.digigoat_login.navigation.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun DashboardScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Greeting and Gauge Meter Section
        Text(
            text = stringResource(id = R.string.greeting, "Rafish Madeta"),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Gauge Meter Kesehatan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        GaugeCarousel()

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Jumlah Kambing",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        GoatCounter()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GaugeCarousel() {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    // Gauge values
    val healthStates = listOf(
        Pair("Sehat", 85),      // Title and value
        Pair("Tidak Sehat", 40)
    )

    // Horizontal Pager
    HorizontalPager(
        count = healthStates.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) { page ->
        // Ambil data berdasarkan indeks halaman
        val (title, value) = healthStates[page]
        GaugeMeterSection(title, value)
    }

    // Indicator Dots untuk menunjukkan halaman aktif
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(healthStates.size) { index ->
            val color = if (pagerState.currentPage == index) Color(0xFFB28704) else Color.LightGray
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .padding(4.dp)
                    .background(color, shape = RoundedCornerShape(50))
            )
        }
    }
}

@Composable
fun GaugeMeterSection(title: String, inputValue: Int) {
    // Animasi untuk inputValue
    val animatedValue by animateFloatAsState(
        targetValue = inputValue.toFloat(),
        animationSpec = tween(durationMillis = 1000) // Durasi animasi 1 detik
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB28704)) // Brown color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB28704)) // Background color
                .padding(16.dp), // Padding for inner spacing
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ProtectionMeter dengan animasi
            ProtectionMeter(
                modifier = Modifier.size(200.dp),
                inputValue = animatedValue.toInt(), // Gunakan nilai yang dianimasikan
                trackColor = Color(0xFFFFFFFF),
                progressColors = listOf(Color(0xFFFFA726), Color(0xFFD84315)),
                innerGradient = Color(0xFFB4FF3B),
                percentageColor = Color.White
            )

            // Teks dengan animasi
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.health_status),
                fontSize = 16.sp,
                color = Color(0xFF4CAF50) // Green color
            )
        }
    }
}

// Helper function to constrain meter values
private fun getMeterValue(inputPercentage: Int): Int {
    return when {
        inputPercentage < 0 -> 0
        inputPercentage > 100 -> 100
        else -> inputPercentage
    }
}

@Composable
fun GoatCounter() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF322405)) // Brown color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.goat),
                contentDescription = "Goat",
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "Total Kambing",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "16",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}