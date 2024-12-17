import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digigoat_login.R
import com.example.digigoat_login.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String, // Dynamic title parameter
    navController: NavController,
    currentRoute: String,
    onNotificationClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Section: Back Icon (visible only on specific screens) and Logo
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    // Back Icon (Visible only on certain screens)
                    if (
                        currentRoute == Screen.Notification.route ||
                        currentRoute == Screen.CatatanPerkembangan.route ||
                        currentRoute == Screen.CatatanPakan.route ||
                        currentRoute == Screen.CatatanKesehatan.route ||
                        currentRoute == Screen.TambahCatatan.route ||
                        currentRoute == Screen.EditProfileScreen.route||
                        currentRoute == Screen.TambahCatatan.route||
                        currentRoute == Screen.DataRecoveryScreen.route
                        ) {

                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp)) // Space between back icon and logo
                    }

                    // Show Logo only on Dashboard
                    if (currentRoute == Screen.Dashboard.route||
                        currentRoute == Screen.Management.route||
                        currentRoute == Screen.ProfileScreen.route) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_digigoat), // Replace with your logo image resource
                            contentDescription = "Logo",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Space between logo and title
                    }

                    // Title Text
                    Text(
                        text = title, // Use the dynamic title here
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Right Section: Notification Icon
                IconButton(onClick = onNotificationClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF322405), // Dark brown color
            titleContentColor = Color.White
        )
    )
}