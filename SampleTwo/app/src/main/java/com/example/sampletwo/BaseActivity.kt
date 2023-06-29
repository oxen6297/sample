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

    fun nextBtnClickListener(activity: Activity) {
        Intent(this, activity::class.java).apply {
            startActivity(this)
        }
    }

    fun Button.clickable(
        resource: Int,
        colorResource: Int,
        isClickable: Boolean
    ) {
        this.setBackgroundResource(resource)
        this.setTextColor(colorResource)
        this.isClickable = isClickable
    }
}