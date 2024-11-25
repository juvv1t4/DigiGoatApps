package com.example.digigoat_login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.digigoat_login.navigation.Screen

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Scrollable Profile Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Profile Header (Avatar and Name)


            // Profile Options wrapped in cards
            Spacer(modifier = Modifier.height(16.dp))
            ProfileOptionItem(
                title = "Edit Profile",
                iconId = R.drawable.profile_img,
                onClick = {navController.navigate(Screen.EditProfileScreen.route)},
                iconTint = Color.Black // Custom tint for the icon
            )
            Spacer(modifier = Modifier.height(16.dp))

            ProfileOptionItem(
                title = "Data Recovery",
                iconId = R.drawable.backup,
                onClick = { navController.navigate(Screen.DataRecoveryScreen.route) },
                iconTint = Color.Black // Custom tint for the icon
            )
            Spacer(modifier = Modifier.height(16.dp))

            ProfileOptionItem(
                title = "Logout",
                iconId = R.drawable.logout,
                onClick = {navController.navigate(Screen.Login.route)},
                iconTint = Color.Black // Custom tint for the icon
            )
        }

        // Spacer to push the footer bar to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // Bottom Bar Navigation (Can be added later if needed)
    }
}


@Composable
fun ProfileOptionItem(
    title: String,
    iconId: Int,
    onClick: () -> Unit,
    subtitle: String? = null, // Optional subtitle for extra information
    iconTint: Color = MaterialTheme.colorScheme.primary // Default tint color is the theme's primary color
) {
    // Card with rounded corners and shadow
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium, // Rounded corners
        elevation = CardDefaults.cardElevation(4.dp), // Add elevation for shadow effect
        colors = CardDefaults.cardColors(containerColor = Color.White) // Set background color for the card
    ) {
        // Row layout for icon and text
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick, indication = null, interactionSource = remember { MutableInteractionSource() }) // Ripple effect on click
                .padding(16.dp) // Padding inside the card
        ) {
            // Icon section with dynamic tint color
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = title,
                modifier = Modifier.size(30.dp), // Scalable icon size
                tint = iconTint // Use the dynamic tint color
            )

            Spacer(modifier = Modifier.width(20.dp))

            // Text section
            Column(modifier = Modifier.weight(1f)) {
                // Title text
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground // Use the onBackground color from the theme
                )

                // Subtitle text (optional)
                subtitle?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) // Lighter color for the subtitle
                    )
                }
            }

            // Optionally, add an arrow or indicator icon to show more details
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right), // Example icon
                contentDescription = "Navigate",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Light tint for the arrow
            )
        }
    }
}
