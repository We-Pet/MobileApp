package com.ipca.wepet.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R

class ContactUsActivity : AppCompatActivity() {

    private lateinit var etMatter: EditText
    private lateinit var etMessage: EditText

    private lateinit var btnSend: Button
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_us_layout)

        initializeElements()
        startNewActivities()
    }

    private fun initializeElements() {
        etMatter = findViewById(R.id.ET_matter)
        etMessage = findViewById(R.id.ET_message)
        btnSend = findViewById(R.id.BTN_send)
        btnBack = findViewById(R.id.BTN_back)
    }

    private fun startNewActivities() {

        // Contact action
        btnSend.setOnClickListener {
            //get input from EditTexts and save in variables
            val subject = etMatter.text.toString().trim()
            val message = etMessage.text.toString().trim()

            sendEmail(subject, message)
        }

        // Back action
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun sendEmail(subject: String, message: String) {
        val recipientEmail = arrayOf("mail.wepet@gmail.com")
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        mIntent.putExtra(Intent.EXTRA_EMAIL, recipientEmail)
        // put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        // put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            // start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }


}