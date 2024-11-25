

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CatatanPakan(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Search bar


            Spacer(modifier = Modifier.height(16.dp))

            // Table
            DataTable()

            Spacer(modifier = Modifier.height(24.dp))

            // Button
            TambahCatatanButton(navController = navController)
        }
    }
}


@Composable
fun DataTable() {
    val headers = listOf("ID Kambing", "Jenis Kambing", "Tanggal", "Umur", "Berat (Kg)", "Je")
    val data = List(7) { List(headers.size) { "Text" } } // Mock data

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5))
                    .padding(vertical = 8.dp)
            ) {
                headers.forEach { header ->
                    Text(
                        text = header,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        items(data) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(Color.White)
                    .border(0.5.dp, Color.Gray, RectangleShape)
            ) {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        fontSize = 12.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun TambahCatatanButton(navController: NavController) {
    Button(
        onClick = { navController.navigate("TambahCatatan") },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E2C00)),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text("Tambah Catatan", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
