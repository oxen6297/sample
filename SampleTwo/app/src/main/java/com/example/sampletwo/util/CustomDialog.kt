package com.example.sampletwo.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import com.example.sampletwo.R

@SuppressLint("InflateParams")
class CustomDialog(context: Context, layoutResource: Int) : AlertDialog(context) {
    private val view by lazy {
        LayoutInflater.from(context).inflate(layoutResource, null)
    }

    private val confirmBtn = view.findViewById<Button>(R.id.btn_confirm)
    private val dialog = Builder(context).setView(view).create()

    init {
        dialog.apply {
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setDialog() {
        dialog.show()
        confirmBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}