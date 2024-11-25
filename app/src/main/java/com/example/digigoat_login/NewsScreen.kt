import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.digigoat_login.R  // Pastikan Anda mengganti dengan nama package Anda
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun NewsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {


        // News Section Header
        NewsSectionHeader()

        // News List
        NewsList()
    }
}



@Composable
fun NewsSectionHeader() {
    var expanded by remember { mutableStateOf(false) }
    var sortBy by remember { mutableStateOf("Terbaru") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Berita Terbaru",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "10 Ditemukan:",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Sort by: $sortBy",
                fontSize = 14.sp,
                color = Color.Gray
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(painter = painterResource(id = R.drawable.sort), contentDescription = "Sort Icon")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

            }
        }
    }
}

@Composable
fun NewsList() {
    val newsItems = listOf(
        NewsItem(
            title = "Pentingnya Nutrisi yang Seimbang Pada Kambing",
            source = "BBC News",
            date = "Jun 9, 2023",
            imageRes = R.drawable.image1
        ),
        NewsItem(
            title = "Perawatan Kambing di Musim Hujan",
            source = "Reuters",
            date = "Jun 8, 2023",
            imageRes = R.drawable.image1
        ),
        NewsItem(
            title = "Tips Menjaga Kesehatan Kambing di Musim Panas",
            source = "AgriTech Daily",
            date = "Jul 5, 2023",
            imageRes = R.drawable.image1
        ),
        NewsItem(
            title = "Menghadapi Penyakit Parasit pada Kambing: Solusi Terbaru",
            source = "Farming Today",
            date = "Jun 15, 2023",
            imageRes = R.drawable.image1
        ),
        NewsItem(
            title = "Inovasi Teknologi untuk Meningkatkan Kualitas Pakan Kambing",
            source = "TechFarm News",
            date = "Jul 1, 2023",
            imageRes = R.drawable.image1
        ),
        NewsItem(
            title = "Pentingnya Pemeriksaan Kesehatan Rutin pada Kambing",
            source = "Farmers Weekly",
            date = "May 20, 2023",
            imageRes = R.drawable.image1
        ),
        NewsItem(
            title = "Mengenal Berbagai Jenis Pakan Organik untuk Kambing",
            source = "Green Livestock Journal",
            date = "Jun 25, 2023",
            imageRes = R.drawable.image1
        ),
        NewsItem(
            title = "Peran Kambing dalam Meningkatkan Kesejahteraan Petani",
            source = "Agriculture News",
            date = "Jul 12, 2023",
            imageRes = R.drawable.image1
        )
    )

    LazyColumn {
        items(newsItems) { item ->
            NewsCard(item)
        }
    }
}

@Composable
fun NewsCard(newsItem: NewsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = newsItem.imageRes),
            contentDescription = newsItem.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = newsItem.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 2,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = newsItem.source,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Verified Source",
                    tint = Color.Blue,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = newsItem.date,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}


// Data class for news item
data class NewsItem(
    val title: String,
    val source: String,
    val date: String,
    val imageRes: Int
)
