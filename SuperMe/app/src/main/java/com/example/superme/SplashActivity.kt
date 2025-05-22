package com.example.superme

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val splashDuration: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // my splash layout

        Handler(Looper.getMainLooper()).postDelayed({
            // Main activity starts after delay
            startActivity(Intent(this, MainActivity::class.java))
            finish() // close splash, no return
        }, splashDuration)
    }
}
