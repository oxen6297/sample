package com.example.sampletwo.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import com.example.sampletwo.R

@SuppressLint("InflateParams")
class CustomDialog(context: Context, layoutResource: Int) {
    private val view = LayoutInflater.from(context).inflate(layoutResource, null)
    private val confirmBtn = view.findViewById<Button>(R.id.btn_confirm)
    private val dialog = AlertDialog.Builder(context).setView(view).create()

    fun setDialog() {
        dialog.show()
        confirmBtn.setOnClickListener {
            dialog.dismiss()
        }
    }
}