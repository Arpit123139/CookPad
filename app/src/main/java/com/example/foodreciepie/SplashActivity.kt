package com.example.foodreciepie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val lottie=findViewById<LottieAnimationView>(R.id.lottes)
        lottie.animate().translationY(-1600f).setDuration(1000).setStartDelay(4000)

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                val intent=Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        },6000)

    }
}