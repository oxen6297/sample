package com.example.sampletwo.extension

import android.content.Context
import android.view.View
import com.example.sampletwo.R

fun Context.qrPopUp(clickConfirm: (View) -> Unit = {}) {
    customDialog(
        textViewText = R.string.dialog_certificate_text,
        btnText = R.string.confirm_btn_text,
        clickConfirm = clickConfirm
    )
}