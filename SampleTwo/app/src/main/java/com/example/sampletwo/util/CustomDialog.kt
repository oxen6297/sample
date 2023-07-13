package com.example.sampletwo.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView

@SuppressLint("InflateParams")
class CustomDialog(private val context: Context, layoutResource: Int) : AlertDialog(context) {

    private val view by lazy {
        LayoutInflater.from(context).inflate(layoutResource, null)
    }
    private val dialog by lazy {
        Builder(context).setView(view).create()
    }
    lateinit var confirmBtn: Button
    lateinit var textContent: TextView

    fun initView(){
        dialog.apply {
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setViewComponent(buttonId: Int, textContentId: Int) {
        confirmBtn = view.findViewById(buttonId)
        textContent = view.findViewById(textContentId)
    }

    fun setViewText(textViewText: Int, btnText: Int){
        confirmBtn.text = context.getString(btnText)
        textContent.text = context.getString(textViewText)
    }

    fun confirmBtn(){
        confirmBtn.setOnClickListener {
            dismissDialog()
        }
    }

    fun showDialog() = dialog.show()

    private fun dismissDialog() = dialog.dismiss()
}