package com.ipca.wepet.presentation.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.util.ToastHandler

private const val MAX_DIGITS = 6

class CodePanelActivity : AppCompatActivity() {
    private val enteredCode = StringBuilder()
    private lateinit var pin: String

    private lateinit var ibClearCode: ImageButton
    private lateinit var tvFinalCode: TextView
    private lateinit var tvForgotPassword: TextView

    private lateinit var tvCode1: TextView
    private lateinit var tvCode2: TextView
    private lateinit var tvCode3: TextView
    private lateinit var tvCode4: TextView
    private lateinit var tvCode5: TextView
    private lateinit var tvCode6: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.code_panel_layout)

        pin = intent.getStringExtra("pin").toString()

        initializeElements()
        startNewActivities()

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

    }

    private fun initializeElements() {
        ibClearCode = findViewById(R.id.IB_clear_code)
        tvFinalCode = findViewById(R.id.TV_final_code)
        tvForgotPassword = findViewById(R.id.TV_forgot_password)

        tvCode1 = findViewById(R.id.TV_code_1)
        tvCode2 = findViewById(R.id.TV_code_2)
        tvCode3 = findViewById(R.id.TV_code_3)
        tvCode4 = findViewById(R.id.TV_code_4)
        tvCode5 = findViewById(R.id.TV_code_5)
        tvCode6 = findViewById(R.id.TV_code_6)
        //btnClearTextPin.setOnClickListener { etPin.text.clear() }
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
        }
    }


    private fun startNewActivities() {
        // Clear text
        ibClearCode.setOnClickListener {
            enteredCode.clear()
            updateCodeView()
        }

        // Reset code
        tvForgotPassword.setOnClickListener {
            val intent = Intent(this, SetUpPinActivity::class.java)
            startActivity(intent)
        }
    }


    fun onNumberButtonClicked(view: View) {
        val button = view as Button
        val number = button.text.toString()

        enteredCode.append(number)
        // Update the code
        tvFinalCode.text = enteredCode.toString()

        setCodeSelected(enteredCode.length)

        if (enteredCode.length == MAX_DIGITS) {
            if (pin == enteredCode.toString()) {
                // Correct PIN entered
                ToastHandler.showToast(this, R.string.correct_pin_entered)
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
            } else {
                // Incorrect PIN entered
                ToastHandler.showToast(this, R.string.incorrect_pin_entered)
                ibClearCode.callOnClick()
            }
        }
    }

    private fun setCodeSelected(number: Int) {
        when (number) {
            1 -> setTextViewSelected(tvCode1)
            2 -> setTextViewSelected(tvCode2)
            3 -> setTextViewSelected(tvCode3)
            4 -> setTextViewSelected(tvCode4)
            5 -> setTextViewSelected(tvCode5)
            6 -> setTextViewSelected(tvCode6)
        }
    }

    private fun setTextViewSelected(textView: TextView) {
        textView.isSelected = true
        textView.setBackgroundResource(R.drawable.code_square_selected)
    }


    private fun updateCodeView() {
        tvCode1.text = ""
        tvCode1.setBackgroundResource(R.drawable.code_square_unselected)
        tvCode2.text = ""
        tvCode2.setBackgroundResource(R.drawable.code_square_unselected)
        tvCode3.text = ""
        tvCode3.setBackgroundResource(R.drawable.code_square_unselected)
        tvCode4.text = ""
        tvCode4.setBackgroundResource(R.drawable.code_square_unselected)
        tvCode5.text = ""
        tvCode5.setBackgroundResource(R.drawable.code_square_unselected)
        tvCode6.text = ""
        tvCode6.setBackgroundResource(R.drawable.code_square_unselected)
        enteredCode.clear()
    }

}
