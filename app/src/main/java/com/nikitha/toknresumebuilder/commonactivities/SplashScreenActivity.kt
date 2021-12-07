package com.nikitha.toknresumebuilder.commonactivities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.nikitha.toknresumebuilder.HolderActivity
import com.nikitha.toknresumebuilder.OnBoardingActivity
import com.nikitha.toknresumebuilder.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        val sharedPreferences = getSharedPreferences("Onboarding", MODE_PRIVATE)
        val firstTime =  sharedPreferences.getBoolean("firstTime", true)
        val sharedPrefEditor = sharedPreferences.edit()

        Handler(Looper.getMainLooper()).postDelayed({
            if(firstTime) {
                val intent = Intent(this, OnBoardingActivity::class.java)
                sharedPrefEditor.putBoolean("firstTime", false)
                sharedPrefEditor.apply()

                startActivity(intent)
                finish()
            }
            else
            {
                val intent = Intent(this, HolderActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 1000)


    }
}