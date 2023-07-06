package com.example.sampletwo.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.example.sampletwo.R

@SuppressLint("InflateParams")
class CustomDialogTwoButton(context: Context, private val switch: MutableLiveData<Boolean>) {

    private val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_two_button, null)
    private val agreeBtn = view.findViewById<Button>(R.id.btn_agree)
    private val notAgreeBtn = view.findViewById<Button>(R.id.btn_not_agree)
    private val dialog = AlertDialog.Builder(context).setView(view).create()

    init {
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun setDialog() {
        dialog.show()
        agreeBtn.setOnClickListener {
            switch.value = true
            dialog.dismiss()
        }
        notAgreeBtn.setOnClickListener {
            switch.value = false
            dialog.dismiss()
        }
    }
}