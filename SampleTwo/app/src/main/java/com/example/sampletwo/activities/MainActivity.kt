package com.example.sampletwo.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.sampletwo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, AccessAuthorizationActivity::class.java).apply {
                startActivity(this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }, 2000)
    }
}