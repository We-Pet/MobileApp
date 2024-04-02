package com.ipca.wepet.controller

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ipca.wepet.R
import com.ipca.wepet.utils.FirebaseUtils
import com.ipca.wepet.views.WePetSplashScreenActivity
class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    private lateinit var btnForgotPass: TextView
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var fireBaseUtils: FirebaseUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        initializeElements()
        startNewActivities()

        /*fireBaseUtils.checkIfUserIsLoggedIn {
            setContentView(R.layout.main_layout)
        }*/
    }

    private fun initializeElements() {
        btnLogin = findViewById(R.id.BTN_login)
        btnCreateAccount = findViewById(R.id.BTN_create_account)
        btnForgotPass = findViewById(R.id.TV_forgot_password)

        auth = Firebase.auth
        fireBaseUtils = FirebaseUtils(auth)
    }

    private fun startNewActivities() {

        //Login action
        btnLogin.setOnClickListener {
            //get email and password inserted
            val email = findViewById<EditText>(R.id.ET_email)
            val password = findViewById<EditText>(R.id.ET_pass)

            //sign in with firebase
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("AUTH", "signInWithEmail:success")
                        //val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Authentication successful.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val intent = Intent(this, WePetSplashScreenActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("AUTH", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
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
    }


    private fun showBottomDialogForgotPassword() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_forgot_password)

        val btnContinue = dialog.findViewById<Button>(R.id.BTN_continue)

        btnContinue.setOnClickListener {
            dialog.dismiss()
            showBottomDialogForgotPasswordCode()
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


        setupTextWatchers(dialog, createElementListForgotPasswordCode(dialog))

        btnContinue.setOnClickListener {
            dialog.dismiss()
            showBottomDialogForgotPasswordReset()
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
        editText1 = dialog.findViewById(R.id.ED_code_1)
        editText2 = dialog.findViewById(R.id.ED_code_2)
        editText3 = dialog.findViewById(R.id.ED_code_3)
        editText4 = dialog.findViewById(R.id.ED_code_4)
        return listOf(editText1, editText2, editText3, editText4)
    }

    private fun showBottomDialogForgotPasswordReset() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout_forgot_password_reset)

        val btnContinue = dialog.findViewById<Button>(R.id.BTN_continue)

        btnContinue.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Password reset", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
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
