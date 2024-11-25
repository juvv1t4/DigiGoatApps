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
            text = stringResource(id = R.string.gauge_meter_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))
        GaugeMeterSection(inputValue = 60) // Dynamic input value
        Spacer(modifier = Modifier.height(24.dp))

        // News Section
        NewsSection(navController)
    }
}

@Composable
fun GaugeMeterSection(inputValue: Int) {
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
                text = stringResource(id = R.string.total_health),
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


@Composable
fun NewsSection(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.news_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        TextButton(onClick = { navController.navigate(Screen.NewsScreen.route) }) {
            Text(text = stringResource(id = R.string.view_all),
                color = Color(0xFFB28704) // Brown color
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Row to hold multiple News Cards
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NewsCard(
            imageRes = R.drawable.image1,
            title = stringResource(id = R.string.news_title_1),
            date = "13 Oktober 2024",
            description = stringResource(id = R.string.news_desc_1)
        )
        NewsCard(
            imageRes = R.drawable.image1,
            title = stringResource(id = R.string.news_title_2),
            date = "13 Oktober 2024",
            description = stringResource(id = R.string.news_desc_2)
        )
    }
}

@Composable
fun NewsCard(imageRes: Int, title: String, date: String, description: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                maxLines = 2,
                color = Color.Gray
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
