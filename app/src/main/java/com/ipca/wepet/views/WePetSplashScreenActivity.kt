package com.ipca.wepet.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.controller.CodePanelActivity
import com.ipca.wepet.controller.LoginActivity


@SuppressLint("CustomSplashScreen")
class WePetSplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 1000
    private val SPLASH_ANIMATION_DURATION: Long = 3000
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_layout)

        val splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_scale)
        val splashScreenImageView = findViewById<ImageView>(R.id.splash_screen_imgV)
        splashScreenImageView.startAnimation(splashAnimation)
        sharedPreferences = getSharedPreferences("AUTH", Context.MODE_PRIVATE)

        val email = sharedPreferences.getString("EMAIL", "")
        val password = sharedPreferences.getString("PASSWORD", "")
        val pin = sharedPreferences.getString("PIN", "")

        Handler(Looper.getMainLooper()).postDelayed({
            if (!email.isNullOrEmpty() && !password.isNullOrEmpty() && !pin.isNullOrEmpty()) {
                val intent = Intent(this, CodePanelActivity::class.java)
                intent.putExtra("pin", pin)
                startActivity(intent)
                overridePendingTransition(R.anim.in_out_activities_enter, R.anim.in_out_activities_exit)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.in_out_activities_enter, R.anim.in_out_activities_exit)
                finish()
            }
        }, SPLASH_DELAY + SPLASH_ANIMATION_DURATION)
    }
}
