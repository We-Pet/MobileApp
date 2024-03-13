package com.ipca.wepet.controller

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.Controller
import com.ipca.wepet.R
import com.ipca.wepet.utils.KeyboardUtils


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val myTextView: TextView = findViewById(R.id.myTextView)
        val myButton: Button = findViewById(R.id.button)
        val myEditText: EditText = findViewById(R.id.editText)
        val controller = Controller()

        myEditText.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                controller.updateTextView(
                    myTextView, myEditText.text.toString()
                )

                KeyboardUtils.hideKeyboard(this)

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        myButton.setOnClickListener {
            controller.updateTextView(
                myTextView, myEditText.text.toString()
            )
        }
    }
}