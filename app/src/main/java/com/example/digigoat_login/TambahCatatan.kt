import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahCatatan() {
    val context = LocalContext.current
    val calendar = remember { java.util.Calendar.getInstance() }
    val year = calendar.get(java.util.Calendar.YEAR)
    val month = calendar.get(java.util.Calendar.MONTH)
    val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)

    var tanggal by remember { mutableStateOf("") }

    Scaffold(
        topBar = {},
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // ID Kambing
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Handle text change */ },
                        label = { Text("ID Kambing") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Tanggal dengan DatePickerDialog
                    OutlinedTextField(
                        value = tanggal,
                        onValueChange = { tanggal = it },
                        label = { Text("Tanggal") },
                        trailingIcon = {
                            IconButton(onClick = {
                                DatePickerDialog(
                                    context,
                                    { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                                        tanggal = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                                    },
                                    year,
                                    month,
                                    day
                                ).show()
                            }) {
                                Icon(Icons.Default.CalendarToday, contentDescription = "Pilih Tanggal")
                            }
                        },
                        readOnly = true, // Tidak bisa diubah manual, hanya lewat DatePicker
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Umur
                    DropdownField(
                        label = "Umur",
                        items = listOf("1 Bulan", "2 Bulan", "3 Bulan", "4 Bulan"),
                        onItemSelected = { selectedUmur -> /* Handle umur selection */ }
                    )

                    // Berat
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Handle text change */ },
                        label = { Text("Berat (Kg)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Jenis Kelamin
                    DropdownField(
                        label = "Jenis Kelamin",
                        items = listOf("Jantan", "Betina"),
                        onItemSelected = { selectedJenisKelamin -> /* Handle jenis kelamin selection */ }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { /* Handle delete action */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFA67C52), // Warna coklat muda
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Text("Hapus")
                    }

                    Button(
                        onClick = { /* Handle add action */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4E342E), // Warna coklat tua
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("Tambah")
                    }
                }
            }
        }
    )
}

@Composable
fun DropdownField(
    label: String,
    items: List<String>,
    selectedValue: String = "", // Nilai awal yang terpilih
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(selectedValue) } // Nilai yang tampil

    Column {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true, // Mencegah input manual
            label = { Text(label) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Icon")
                }
            },
            placeholder = { Text("Pilih $label") }, // Placeholder jika kosong
            modifier = Modifier.fillMaxWidth()
        )

        // Menampilkan dropdown jika expanded true
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedItem = item
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
