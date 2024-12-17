import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.digigoat_login.navigation.Screen

@Composable
fun BottomNavbar(navController: NavController) {
    // Selected state tracking the selected item
    val selectedItem = remember { mutableStateOf("Beranda") }

    val backgroundColor = Color(0xFF322405) // Warna coklat
    val selectedColor = Color(0xFFD2A73F)   // Warna emas
    val unselectedColor = Color.White

    // Listen to the current screen route to update the selected item dynamically
    LaunchedEffect(navController.currentBackStackEntryFlow) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            selectedItem.value = when (backStackEntry.destination.route) {
                Screen.Dashboard.route -> "Beranda"
                Screen.Management.route -> "Manajemen"
                Screen.ProfileScreen.route -> "Profile"
                else -> "Beranda" // default to "Beranda" if not matched
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor,
        shadowElevation = 4.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(horizontal = 16.dp)
        ) {
            // Each navigation item
            BottomNavItem(
                label = "Beranda",
                icon = Icons.Default.Home,
                isSelected = selectedItem.value == "Beranda",
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = {
                    selectedItem.value = "Beranda"
                    navController.navigate(Screen.Dashboard.route)
                }
            )

            BottomNavItem(
                label = "Manajemen",
                icon = Icons.Default.Edit,
                isSelected = selectedItem.value == "Manajemen",
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = {
                    selectedItem.value = "Manajemen"
                    navController.navigate(Screen.Management.route)
                }
            )

            BottomNavItem(
                label = "Profile",
                icon = Icons.Default.Person,
                isSelected = selectedItem.value == "Profile",
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = {
                    selectedItem.value = "Profile"
                    navController.navigate(Screen.ProfileScreen.route)
                }
            )
        }
    }
}


@Composable
fun BottomNavItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    onClick: () -> Unit
) {
    // Animate color change for icon and text
    val animatedColor = animateColorAsState(
        targetValue = if (isSelected) selectedColor else unselectedColor
    )

    // Animate icon size change using spring-based animation
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 36.dp else 24.dp,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 100f)
    )

    // Animate shadow elevation for 3D effect
    val shadowElevation by animateDpAsState(
        targetValue = if (isSelected) 6.dp else 0.dp,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 50f)
    )

    // Animate text size with TextUnit instead of Dp for font size
    val textSize by animateFloatAsState(
        targetValue = if (isSelected) 14f else 12f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 100f)
    )

    // Animate text padding with Dp (no change needed here)
    val textPadding by animateDpAsState(
        targetValue = if (isSelected) 8.dp else 6.dp,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 100f)
    )

    // Set background color for selected item
    val textBackgroundColor = if (isSelected) selectedColor.copy(alpha = 0.2f) else Color.Transparent

    // Opacity animation for the text to create a fade effect
    val textOpacity by animateFloatAsState(targetValue = if (isSelected) 1f else 0.6f)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .animateContentSize() // Animate size change for the whole component
    ) {
        // Icon with shadow effect for a 3D feel when selected
        Box(
            modifier = Modifier
                .size(iconSize) // Apply the animated icon size
                .shadow(
                    elevation = shadowElevation, // Animated shadow elevation
                    shape = CircleShape
                )
                .graphicsLayer {
                    scaleX = if (isSelected) 1.2f else 1f
                    scaleY = if (isSelected) 1.2f else 1f
                }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = animatedColor.value,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Text with animated background and fade effect
        Text(
            text = label,
            fontSize = textSize.sp, // Corrected to use TextUnit (sp) for font size
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = animatedColor.value.copy(alpha = textOpacity), // Animated opacity for text
            modifier = Modifier
                .background(textBackgroundColor, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = textPadding, vertical = 4.dp) // Animated text padding
        )
    }
}