package com.ipca.wepet.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ipca.wepet.R
import com.ipca.wepet.controller.LoginActivity


@SuppressLint("CustomSplashScreen")
class WePetSplashScreenActivity: AppCompatActivity() {

    private val SPLASH_DELAY: Long = 1000
    private val SPLASH_ANIMATION_DURATION: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_layout)

        val splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_screen_scale)
        val splashScreenImageView = findViewById<ImageView>(R.id.splash_screen_imgV)
        splashScreenImageView.startAnimation(splashAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.in_out_activities_enter, R.anim.in_out_activities_exit)
            finish()
        }, SPLASH_DELAY + SPLASH_ANIMATION_DURATION)
    }
}
