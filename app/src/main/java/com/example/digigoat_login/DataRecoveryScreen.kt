
import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.digigoat_login.R
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataRecoveryScreen(navController: NavHostController) {
    var syncProgress by remember { mutableStateOf(80) }
    var exportFormat by remember { mutableStateOf("CSV") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Scaffold(
        // Removed the topBar section to delete the top bar
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(16.dp)
        ) {
            // Section: Ekspor Data
            Text(
                text = "Ekspor Data",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Format Ekspor",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = exportFormat,
                        color = Color.Gray
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.vector),
                        contentDescription = "Select Format Ekspor",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Rentang Tanggal
            Text(
                text = "Rentang Tanggal",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val boxModifier = Modifier
                    .weight(1f)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp)
                    .height(35.dp)

                BasicTextField(
                    value = startDate,
                    onValueChange = { startDate = it },
                    modifier = boxModifier,
                    textStyle = TextStyle(fontSize = 12.sp),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (startDate.isEmpty()) {
                                Text(
                                    text = "hari/tanggal/tahun",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Clip
                                )
                            } else {
                                innerTextField()
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.kalender),
                                contentDescription = "Start Date",
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable {
                                        DatePickerDialog(
                                            context,
                                            { _, year, month, dayOfMonth ->
                                                startDate = "$dayOfMonth/${month + 1}/$year"
                                            },
                                            calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH)
                                        ).show()
                                    },
                                tint = Color.Black
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.width(16.dp))

                BasicTextField(
                    value = endDate,
                    onValueChange = { endDate = it },
                    modifier = boxModifier,
                    textStyle = TextStyle(fontSize = 12.sp),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (endDate.isEmpty()) {
                                Text(
                                    text = "hari/tanggal/tahun",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Clip
                                )
                            } else {
                                innerTextField()
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.kalender),
                                contentDescription = "End Date",
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable {
                                        DatePickerDialog(
                                            context,
                                            { _, year, month, dayOfMonth ->
                                                endDate = "$dayOfMonth/${month + 1}/$year"
                                            },
                                            calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH)
                                        ).show()
                                    },
                                tint = Color.Black
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /* Handle Ekspor */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B5E00)),
                    modifier = Modifier
                        .width(200.dp) // Set specific width
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Ekspor Data",
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp)) // Spasi sebelum elemen berikutnya

            // Ikon dan progres sinkronisasi
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Proses Ekspor Data",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "${syncProgress.toInt()}%",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = syncProgress / 100f,
                    color = Color.Black,
                    trackColor = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }
    }
}
