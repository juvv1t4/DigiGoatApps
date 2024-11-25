import android.app.DatePickerDialog
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digigoat_login.R
import java.text.SimpleDateFormat
import java.util.*

data class ProfileState(
    val profileImageUri: Uri? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val birthDate: String = "",
    val error: String? = null
)

sealed class ProfileEvent {
    data class UpdateProfileImage(val uri: Uri) : ProfileEvent()
    data class UpdateName(val name: String) : ProfileEvent()
    data class UpdateEmail(val email: String) : ProfileEvent()
    data class UpdatePassword(val password: String) : ProfileEvent()
    data class UpdateBirthDate(val birthDate: String) : ProfileEvent()
    object SaveProfile : ProfileEvent()
}

class ProfileViewModel : ViewModel() {
    var state by mutableStateOf(ProfileState())
        private set

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdateProfileImage -> {
                state = state.copy(profileImageUri = event.uri)
            }
            is ProfileEvent.UpdateName -> {
                if (validateName(event.name)) {
                    state = state.copy(name = event.name, error = null)
                } else {
                    state = state.copy(error = "Nama harus memiliki minimal 3 karakter")
                }
            }
            is ProfileEvent.UpdateEmail -> {
                if (validateEmail(event.email)) {
                    state = state.copy(email = event.email, error = null)
                } else {
                    state = state.copy(error = "Format email tidak valid")
                }
            }
            is ProfileEvent.UpdatePassword -> {
                if (validatePassword(event.password)) {
                    state = state.copy(password = event.password, error = null)
                } else {
                    state = state.copy(error = "Password harus minimal 6 karakter")
                }
            }
            is ProfileEvent.UpdateBirthDate -> {
                if (validateBirthDate(event.birthDate)) {
                    state = state.copy(birthDate = event.birthDate, error = null)
                } else {
                    state = state.copy(error = "Format tanggal harus dd/mm/yyyy")
                }
            }
            is ProfileEvent.SaveProfile -> {
                saveProfile()
            }

            else -> {}
        }
    }

    private fun validateName(name: String): Boolean = name.length >= 3
    private fun validateEmail(email: String): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun validatePassword(password: String): Boolean = password.length >= 6
    private fun validateBirthDate(birthDate: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(birthDate)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun saveProfile() {
        if (!validateName(state.name) ||
            !validateEmail(state.email) ||
            !validatePassword(state.password) ||
            !validateBirthDate(state.birthDate)
        ) {
            state = state.copy(error = "Mohon periksa kembali semua field")
            return
        }
        state = state.copy(error = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = viewModel()
    val state = profileViewModel.state
    var profileImageUri by remember { mutableStateOf(state.profileImageUri) }
    var name by remember { mutableStateOf(state.name) }
    var email by remember { mutableStateOf(state.email) }
    var password by remember { mutableStateOf(state.password) }
    var birthDate by remember { mutableStateOf(state.birthDate) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            birthDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                profileImageUri = it
                profileViewModel.onEvent(ProfileEvent.UpdateProfileImage(it))
            }
        }
    )

    Scaffold(
        topBar = {}, // Removed the top bar
        content = { paddingValues ->
            ProfileContent(
                paddingValues = paddingValues,
                profileImageUri = profileImageUri,
                name = name,
                email = email,
                password = password,
                birthDate = birthDate,
                onProfileImageClick = { imagePickerLauncher.launch("image/*") },
                onNameChange = { name = it },
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onBirthDateClick = { datePickerDialog.show() },
                onSaveChanges = {
                    profileViewModel.onEvent(ProfileEvent.UpdateName(name))
                    profileViewModel.onEvent(ProfileEvent.UpdateEmail(email))
                    profileViewModel.onEvent(ProfileEvent.UpdatePassword(password))
                    profileViewModel.onEvent(ProfileEvent.UpdateBirthDate(birthDate))
                    profileViewModel.onEvent(ProfileEvent.SaveProfile)
                    navController.navigateUp()
                }
            )
        }
    )
}


@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    profileImageUri: Uri?,
    name: String,
    email: String,
    password: String,
    birthDate: String,
    onProfileImageClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBirthDateClick: () -> Unit,
    onSaveChanges: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        // Profile Image Section
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .align(Alignment.CenterHorizontally)
                .clickable { onProfileImageClick() }
        ) {
            if (profileImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = profileImageUri),
                    contentDescription = "Selected Profile Picture",
                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.profile_img),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Gray
                )
            }
        }

        // Spacer after profile image
        Spacer(modifier = Modifier.height(16.dp))

        // Nama Section
        InputBox("Nama", name, onValueChange = onNameChange)

        // Spacer between Nama and Email
        Spacer(modifier = Modifier.height(16.dp))

        // Email Section
        InputBox("Email", email, onValueChange = onEmailChange)

        // Spacer between Email and Password
        Spacer(modifier = Modifier.height(16.dp))

        // Password Section
        InputBox("Password", password, onValueChange = onPasswordChange)

        // Spacer between Password and Birthdate
        Spacer(modifier = Modifier.height(16.dp))

        // Birthdate Section
        Text(
            text = "Tanggal Lahir",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .clickable { onBirthDateClick() },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (birthDate.isEmpty()) "dd/mm/yyyy" else birthDate,
                    color = if (birthDate.isEmpty()) Color.Gray else Color.Black,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.kalender),
                    contentDescription = "Select Date",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // Spacer before Save button
        Spacer(modifier = Modifier.height(30.dp))

        // Save button Section
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center 
        ) {
            Button(
                onClick = onSaveChanges,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B5E00)),
                modifier = Modifier.width(180.dp)
            ) {
                Text(text = "Simpan Perubahan", color = Color.White)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBox(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

