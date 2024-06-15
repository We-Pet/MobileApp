package com.ipca.wepet.presentation.controller

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ipca.wepet.R
import com.ipca.wepet.util.EmailUtils
import com.ipca.wepet.util.FirebaseUtils
import com.ipca.wepet.util.KeyboardUtils
import com.ipca.wepet.util.ToastHandler

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    private lateinit var btnForgotPass: TextView
    private lateinit var tvSetupPin: TextView

    private lateinit var btnClearTextEmail: ImageButton
    private lateinit var btnClearTextPassword: ImageButton

    private lateinit var btnShowOrHidePassword: ImageButton

    private lateinit var badLoginWarning: TextView

    private lateinit var emailTextField: EditText
    private lateinit var passwordTextField: EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var fireBaseUtils: FirebaseUtils

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        initializeElements()
        startNewActivities()

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        /*fireBaseUtils.checkIfUserIsLoggedIn {
            setContentView(R.layout.main_layout)
        }*/
    }

    private fun initializeElements() {

        btnLogin = findViewById(R.id.BTN_login)
        btnCreateAccount = findViewById(R.id.BTN_create_account)
        btnForgotPass = findViewById(R.id.TV_forgot_password)
        tvSetupPin = findViewById(R.id.TV_setup_pin)

        btnClearTextEmail = findViewById(R.id.IBTN_clear_button_email)
        btnClearTextPassword = findViewById(R.id.IBTN_clear_button_password)
        badLoginWarning = findViewById(R.id.bad_login_warning_TV)
        emailTextField = findViewById(R.id.ET_email)
        passwordTextField = findViewById(R.id.ET_password)
        btnClearTextEmail.setOnClickListener { emailTextField.text.clear() }
        btnClearTextPassword.setOnClickListener { passwordTextField.text.clear() }

        btnShowOrHidePassword = findViewById(R.id.IBTN_show_or_hide_password)

        auth = Firebase.auth
        fireBaseUtils = FirebaseUtils(auth)

        sharedPreferences = getSharedPreferences("AUTH", Context.MODE_PRIVATE)
    }

    private fun startNewActivities() {

        //Login action
        btnLogin.setOnClickListener {
            checkLoginFieldsAndValidate()
        }

        btnShowOrHidePassword.setOnClickListener {
            if (passwordTextField.transformationMethod == PasswordTransformationMethod.getInstance()) {
                passwordTextField.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                btnShowOrHidePassword.setImageResource(R.drawable.hide_password)
            } else {
                passwordTextField.transformationMethod = PasswordTransformationMethod.getInstance()
                btnShowOrHidePassword.setImageResource(R.drawable.show_password)
            }
        }

        //Create account action
        btnCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        //Forgot pass action
        btnForgotPass.setOnClickListener {
            showBottomDialogForgotPassword()
        }

        // Trigger login action on Enter press
        passwordTextField.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                KeyboardUtils.hideKeyboard(this)
                checkLoginFieldsAndValidate()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        // Set up pin to authenticate
        tvSetupPin.setOnClickListener {
            val intent = Intent(this, SetUpPinActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkLoginFieldsAndValidate() {

        val email = emailTextField.text.toString()
        val password = passwordTextField.text.toString()

        if (email.isBlank()) {
            ToastHandler.showToast(this, R.string.error_empty_email)
            return
        } else if (!EmailUtils.isEmailValid(email)) {
            ToastHandler.showToast(this, R.string.error_empty_email)
        } else if (password.isBlank()) {
            ToastHandler.showToast(this, R.string.error_empty_password)
        } else {
            loginFireBase(email, password)
        }
    }

    private fun loginFireBase(email: String, password: String) {

        //sign in with firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("AUTH", "signInWithEmail:success")
                    //val user = auth.currentUser
                    ToastHandler.showToast(baseContext, R.string.authentication_successful)
                    setSharedPreferences(email, password)
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("AUTH", "signInWithEmail:failure", task.exception)
                    ToastHandler.showToast(baseContext, R.string.authentication_failed)
                }
            }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
        }
    }

    private fun setSharedPreferences(email: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("EMAIL", email)
        editor.putString("PASSWORD", password)
        editor.apply()
    }

    private fun showBottomDialogForgotPassword() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_forgot_password)

        val btnContinue = dialog.findViewById<Button>(R.id.BTN_continue)
        val btnClearText = dialog.findViewById<ImageButton>(R.id.IBTN_clear_button_email)

        val emailText = dialog.findViewById<EditText>(R.id.ET_email)

        btnClearText.setOnClickListener { emailText.text.clear() }

        btnContinue.setOnClickListener {

            val email = emailText.text.toString()

            if (email.isBlank()) {
                ToastHandler.showToast(this, R.string.error_empty_email)
                return@setOnClickListener
            } else if (!EmailUtils.isEmailValid(email)) {
                ToastHandler.showToast(this, R.string.error_invalid_email)
                return@setOnClickListener
            } else {
                dialog.dismiss()
                showBottomDialogForgotPasswordCode()
            }
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun showBottomDialogForgotPasswordCode() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_forgot_password_code)

        val btnContinue = dialog.findViewById<Button>(R.id.BTN_continue)

        val codeEditTextFieldsList = createElementListForgotPasswordCode(dialog)

        setupTextWatchers(dialog, codeEditTextFieldsList)

        btnContinue.setOnClickListener {
            if (isCodeFieldsNotEmpty(codeEditTextFieldsList)) {
                dialog.dismiss()
                showBottomDialogForgotPasswordReset()
            }
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun showBottomDialogForgotPasswordReset() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_forgot_password_reset)

        val btnContinue = dialog.findViewById<Button>(R.id.BTN_submit_confirm_password)
        val btnClearTextPassword =
            dialog.findViewById<ImageButton>(R.id.IBTN_clear_button_password_reset)
        val btnClearTextConfirmPassword =
            dialog.findViewById<ImageButton>(R.id.IBTN_clear_button_confirm_password_reset)

        val passwordText = dialog.findViewById<EditText>(R.id.ET_password_reset)
        val confirmPasswordText =
            dialog.findViewById<EditText>(R.id.ET_repeat_confirm_password_reset)

        btnClearTextPassword.setOnClickListener { passwordText.text.clear() }
        btnClearTextConfirmPassword.setOnClickListener { confirmPasswordText.text.clear() }

        btnContinue.setOnClickListener {
            if (passwordText.text.isBlank()) {
                ToastHandler.showToast(this, R.string.error_empty_password)
                return@setOnClickListener
            } else if (confirmPasswordText.text.toString() != passwordText.text.toString()) {
                ToastHandler.showToast(this, R.string.passwords_do_not_match)
                return@setOnClickListener
            } else {
                dialog.dismiss()
                ToastHandler.showToast(this, R.string.password_reset)
            }
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

    private fun createElementListForgotPasswordCode(dialog: Dialog): List<EditText> {
        val editText1 = dialog.findViewById<EditText>(R.id.ED_code_1)
        val editText2 = dialog.findViewById<EditText>(R.id.ED_code_2)
        val editText3 = dialog.findViewById<EditText>(R.id.ED_code_3)
        val editText4 = dialog.findViewById<EditText>(R.id.ED_code_4)
        return listOf(editText1, editText2, editText3, editText4)
    }

    private fun isCodeFieldsNotEmpty(editTextList: List<EditText>): Boolean {
        for (editText in editTextList) {
            if (editText.text.isNullOrEmpty()) return false
        }
        return true
    }

    private fun setupTextWatchers(dialog: Dialog, listEditTexts: List<EditText>) {
        for ((index, editText) in listEditTexts.withIndex()) {

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        val nextIndex = index + 1
                        if (nextIndex < listEditTexts.size) {
                            // Request focus to the next editText
                            listEditTexts[nextIndex].requestFocus()
                        } else {
                            // If is the last show next dialog
                            dialog.dismiss()
                            showBottomDialogForgotPasswordReset()
                        }
                    }
                }
            })
        }
    }
}
