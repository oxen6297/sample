package com.example.sampletwo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity() {
    val binding by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun nextBtnClickListener(button: Button, activity: Activity) {
        button.setOnClickListener {
            val intent = Intent(this, activity::class.java)
            startActivity(intent)
        }
    }

    fun backBtnClickListener(button: Button){
        button.setOnClickListener {
            finish()
        }
    }
}