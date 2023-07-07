package com.example.sampletwo.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import com.example.sampletwo.R
import com.example.sampletwo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.textSplashLogoText.run {
            val builder = SpannableStringBuilder(text).apply {
                setSpan(ForegroundColorSpan(getColor(R.color.orange)), 9, 15, 0)
                setSpan(ForegroundColorSpan(getColor(R.color.orange)), 19, 28, 0)
                setSpan(RelativeSizeSpan(1.25f), 9, 15, 0)
                setSpan(RelativeSizeSpan(1.25f), 19, 28, 0)
            }
            text = builder
        }
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, MainScreenActivity::class.java).apply {
                startActivity(this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }, 2000)
    }
}