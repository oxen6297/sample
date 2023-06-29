package com.example.sampletwo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity() {
    val binding by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun nextBtnClickListener(activity: Activity) {
        Intent(this, activity::class.java).apply {
            startActivity(this)
        }
    }
}