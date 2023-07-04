package com.example.sampletwo.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sampletwo.R
import com.example.sampletwo.databinding.ActivityMainBinding
import com.example.sampletwo.extension.spannableStringBuilder

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.textSplashLogoText.apply {
            spannableStringBuilder(getColor(R.color.orange), 9, 15, Typeface.NORMAL, 1.25f)
            spannableStringBuilder(getColor(R.color.orange), 19, 28, Typeface.NORMAL, 1.25f)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, MainScreenActivity::class.java).apply {
                startActivity(this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }, 2000)
    }
}