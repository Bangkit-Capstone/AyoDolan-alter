package com.dicoding.capstone.ayodolan.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dicoding.capstone.ayodolan.R
import com.dicoding.capstone.ayodolan.ui.home.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object{
        const val DELAY_MILLS = 3000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, DELAY_MILLS.toLong())
    }
}