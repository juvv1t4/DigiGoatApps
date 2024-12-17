package com.example.digigoat_login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.digigoat_login.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagementScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.background(Color(0xFFEDEDED))
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        ) {
            MenuItem("Catatan Perkembangan", painterResource(R.drawable.image_goat1)) {
                navController.navigate(Screen.CatatanPerkembangan.route)
            }
            MenuItem("Catatan Pakan", painterResource(R.drawable.image_goat2)) {
                navController.navigate(Screen.CatatanPakan.route)
            }
            MenuItem("Catatan Kesehatan", painterResource(R.drawable.image_goat3)) {
                navController.navigate(Screen.CatatanKesehatan.route)
            }
        }
    }
}


@Composable
fun MenuItem(title: String, image: Painter, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onClick)
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = image,
                contentDescription = "Image representing $title",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color(0xAA3D2B1F))
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD2A73F),
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        }
    }
}
