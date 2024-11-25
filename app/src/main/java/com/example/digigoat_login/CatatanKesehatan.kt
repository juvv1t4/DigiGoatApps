import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HealthRecordScreen() {
    // Wrapping the main content in a LazyColumn to make it scrollable
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { CalendarSection() }
        item { UpcomingSchedule() }
        item { SearchBar() }
        item { RecordTable() }
        item { AddRecordButton() }
    }
}

@Composable
fun CalendarSection() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Nov 2024", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            IconButton(onClick = { /* Handle add calendar item */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }

        CalendarGrid()
    }
}

@Composable
fun CalendarGrid() {
    val days = listOf("S", "M", "T", "W", "T", "F", "S")
    val dates = (1..30).toList()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFD88A), shape = RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            days.forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        dates.chunked(7).forEach { week ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                week.forEach { date ->
                    Text(
                        text = date.toString(),
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun UpcomingSchedule() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Jadwal Mendatang", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "23 NOV",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Column {
                    Text(text = "Pemberian vitamin", fontWeight = FontWeight.SemiBold)
                    Text(
                        text = "23 Nov - 24 Nov\nPemberian vitamin D",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = { /* Handle search */ },
        placeholder = { Text(text = "Cari") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun RecordTable() {
    val headers = listOf("ID Kambing", "Tanggal", "Vaksin", "Obat", "Penyakit")
    val records = remember { List(10) { "Text" } } // Placeholder data

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        // Table Header
        Row(modifier = Modifier.fillMaxWidth()) {
            headers.forEach { header ->
                Text(
                    text = header,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Divider(color = Color.Gray)

        // Replace LazyColumn with a simple Column
        records.forEach {
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(headers.size) {
                    Text(
                        text = "Text",
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun AddRecordButton() {
    Button(
        onClick = { /* Handle add record */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6E4C1B),
            contentColor = Color.White
        )
    ) {
        Text(text = "Tambah Catatan")
    }
}
