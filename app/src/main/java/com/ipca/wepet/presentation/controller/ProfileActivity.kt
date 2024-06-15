package com.ipca.wepet.presentation.controller

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.ipca.wepet.R
import com.ipca.wepet.domain.model.UserModel
import com.ipca.wepet.presentation.fragment.user.UserViewModel
import com.ipca.wepet.util.ToastHandler
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private val CAMERA_REQUEST = 1888
    private lateinit var ivMainPhoto: ImageView
    private lateinit var tvMainName: TextView

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPhone: EditText

    private lateinit var btnSave: Button
    private lateinit var btnBack: ImageButton

    private lateinit var ibName: ImageButton
    private lateinit var ibEmail: ImageButton
    private lateinit var ibAddress: ImageButton
    private lateinit var ibPassword: ImageButton
    private lateinit var ibPhone: ImageButton

    private var user: UserModel? = null

    private lateinit var ibShowOrHidePassword: ImageButton

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_layout)

        initializeElements()
        startNewActivities()

        // Request camera permission if not already granted
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST
            )
        }

        if (isInternetAvailable()) {
            extracted()
            observeUserData()
        } else {
            ToastHandler.showToast(this, R.string.no_internet)
        }
    }

    // Function to get user data using shared preferences
    private fun extracted() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("AUTH", MODE_PRIVATE)

        // To get user by email
        sharedPreferences.getString("EMAIL", "")?.let { userViewModel.getUserByEmail(it) }
    }

    // Function to observe user data changes
    private fun observeUserData() {
        userViewModel.userState.observe(this, Observer { userState ->
            // Handle changes in user state here
            userState.user?.let { user ->
                // User data is not null, update UI or perform actions
                Log.d("ProfileActivity", "User loaded: $user")
                updateUI(user)
                this.user = user
            }

            // Handle loading and error states if needed
            userState.error?.let { errorMessage ->
                // Show error message to the user
                onBackPressedDispatcher.onBackPressed()
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("ProfileActivity", errorMessage)
            }
        })
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnected ?: false
        }
    }


    // Function to update UI elements with user data
    private fun updateUI(user: UserModel) {
        tvMainName.text = user.name
        etEmail.hint = user.email
        etName.hint = user.name

        user.phoneNumber?.let { phoneNumber ->
            etPhone.hint = phoneNumber
        }

        user.city?.let { address ->
            etAddress.hint = address
        }

        // Load profile image using Glide or another image loading library
        Glide.with(this)
            .load(user.profileImageUrl)
            .into(ivMainPhoto)
    }

    // Handle the result of permission requests
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ToastHandler.showToast(baseContext, R.string.camera_permission_granted)
            } else {
                ToastHandler.showToast(baseContext, R.string.camera_permission_denied)
            }
        }
    }

    // Function to initialize UI elements
    private fun initializeElements() {
        ivMainPhoto = findViewById(R.id.IV_main_photo)
        tvMainName = findViewById(R.id.TV_main_name)
        etName = findViewById(R.id.ET_name)
        etEmail = findViewById(R.id.ET_email)
        etAddress = findViewById(R.id.ET_address)
        etPassword = findViewById(R.id.ET_password)
        etPhone = findViewById(R.id.ET_phone)
        btnSave = findViewById(R.id.BTN_save)
        btnBack = findViewById(R.id.IB_back)
        ibName = findViewById(R.id.IBTN_clear_button_name)
        ibEmail = findViewById(R.id.IBTN_clear_button_email)
        ibAddress = findViewById(R.id.IBTN_clear_button_address)
        ibPassword = findViewById(R.id.IBTN_clear_button_password)
        ibPhone = findViewById(R.id.IBTN_clear_button_phone)
        ibShowOrHidePassword = findViewById(R.id.IBTN_show_or_hide_password)
    }

    // Function to set up button click listeners and other UI interactions
    private fun startNewActivities() {
        // Login action
        btnSave.setOnClickListener {

            //Call database
            if (isInternetAvailable()) {
                updateUser()
                storeInSharedPreferences()
                pressAllClearButtons()
                ToastHandler.showToast(baseContext, R.string.data_saved_successfully)
            } else {
                ToastHandler.showToast(this, R.string.no_internet)
            }
        }

        clearButtons()

        // Button to show or hide password
        ibShowOrHidePassword.setOnClickListener {
            if (etPassword.transformationMethod == PasswordTransformationMethod.getInstance()) {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ibShowOrHidePassword.setImageResource(R.drawable.hide_password)
            } else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                ibShowOrHidePassword.setImageResource(R.drawable.show_password)
            }
        }

        // Back action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ivMainPhoto.setOnClickListener {
            openCamera()
        }
    }

    private fun clearButtons() {
        ibName.setOnClickListener { etName.text.clear() }
        ibPhone.setOnClickListener { etPhone.text.clear() }
        ibAddress.setOnClickListener { etAddress.text.clear() }
        ibPassword.setOnClickListener { etPassword.text.clear() }
    }

    private fun pressAllClearButtons() {
        val buttons = listOf(ibName, ibPhone, ibAddress, ibPassword)
        buttons.forEach { button ->
            button.performClick()
        }
    }


    // Function to store user data in shared preferences
    private fun storeInSharedPreferences() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("AUTH", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val oldPassword: String = sharedPreferences.getString("PASSWORD", "").toString()

        // Define the fields you want to save
        val fieldsToSave = listOf("NAME", "PASSWORD")

        for (field in fieldsToSave) {
            val editText = when (field) {
                "NAME" -> etName
                "PASSWORD" -> etPassword
                else -> null
            }

            // Check if editText is null or empty to skip saving
            editText?.let {
                val text = it.text.toString().trim()  // Trim whitespace
                if (text.isNotEmpty()) {
                    editor.putString(field, text)
                }
            }
        }

        editor.apply()

        // Check if the new password is not empty before updating Firebase
        val newPassword: String = etPassword.text.toString().trim()
        if (newPassword.isNotEmpty()) {
            val email: String = sharedPreferences.getString("EMAIL", "").toString()
            // Call the function to update the password in Firebase
            updatePasswordFirebase(email, newPassword, oldPassword)
        }
    }


    // After saving data, update password in Firebase
    private fun updatePasswordFirebase(email: String, password: String, oldPassword: String) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val credential = EmailAuthProvider.getCredential(email, oldPassword)
            user.reauthenticate(credential).addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    user.updatePassword(password)
                        .addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                Log.d("AUTH", "Password updated successfully")
                            } else {
                                Log.e("AUTH", "Password update failed", updateTask.exception)
                            }
                        }
                } else {
                    Log.e("AUTH", "Re-authentication failed", authTask.exception)
                }
            }
        }
    }

    // Launcher to handle camera activity result
    private var cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val photo = result.data?.extras?.get("data") as Bitmap?
                photo?.let { bitmap ->
                    ivMainPhoto.setImageBitmap(bitmap)
                }
            } else if (result.resultCode == RESULT_CANCELED) {
                ToastHandler.showToast(this, R.string.camera_cancel)
            }
        }

    // Function to upload image to server
    private fun updateUser() {
        if (user == null || user!!.id == null) {
            ToastHandler.showToast(this, R.string.error_upload_image)
            return
        }

        // Get the bitmap from ImageView
        val defaultBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)

        // Get the bitmap from ImageView
        val bitmap = (ivMainPhoto.drawable as? BitmapDrawable)?.bitmap ?: defaultBitmap

        // Convert bitmap to file
        val file = convertBitmapToFile(bitmap, "profile_image.jpg")
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("image", file.name, requestBody)

        val userName = if (etName.text.isBlank()) user!!.name else etName.text.toString()
        val userPhone = if (etPhone.text.isBlank()) user!!.phoneNumber else etPhone.text.toString()
        val userAddress = if (etAddress.text.isBlank()) user!!.city else etAddress.text.toString()

        userViewModel.updateUser(
            user!!.id!!,
            multipartBody,
            userName,
            userPhone,
            userAddress
        )
    }

    private fun convertBitmapToFile(bitmap: Bitmap, fileName: String): File {
        // Create a file to write the bitmap data
        val file = File(cacheDir, fileName)
        file.outputStream().use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        return file
    }

    // Function to open the camera
    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }
}