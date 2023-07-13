package com.example.sampletwo.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView

@SuppressLint("InflateParams")
class CustomDialogTwoButton(private val context: Context, layoutResource: Int) :
    AlertDialog(context) {

    private val view by lazy {
        LayoutInflater.from(context).inflate(layoutResource, null)
    }
    private val dialog by lazy {
        Builder(context).setView(view).create()
    }
    lateinit var confirmBtn: Button
    lateinit var cancelBtn: Button
    lateinit var textTitle: TextView
    lateinit var textContent: TextView

    fun initView() {
        dialog.apply {
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun setViewComponent(
        confirmButtonId: Int,
        cancelBtnId: Int,
        textTitleId: Int,
        textContentId: Int
    ) {
        confirmBtn = view.findViewById(confirmButtonId)
        cancelBtn = view.findViewById(cancelBtnId)
        textTitle = view.findViewById(textTitleId)
        textContent = view.findViewById(textContentId)
    }

    fun setViewText(confirmBtnText: Int, cancelBtnText: Int, titleText: Int, contentText: Int) {
        confirmBtn.text = context.getString(confirmBtnText)
        cancelBtn.text = context.getString(cancelBtnText)
        textTitle.text = context.getString(titleText)
        textContent.text = context.getString(contentText)
    }

    fun confirmBtn(confirmClickListener: (View) -> Unit) {
        confirmBtn.setOnClickListener {
            confirmClickListener(it)
            dismissDialog()
        }
    }

    fun cancelBtn(cancelClickListener: (View) -> Unit) {
        cancelBtn.setOnClickListener {
            cancelClickListener(it)
            dismissDialog()
        }
    }

    fun showDialog() = dialog.show()

    private fun dismissDialog() = dialog.dismiss()
}