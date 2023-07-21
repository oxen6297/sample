package com.example.sampletwo.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import com.example.sampletwo.R

fun Context.qrPopUp(clickConfirm: (View) -> Unit = {}) {
    customDialog(
        textViewText = R.string.dialog_certificate_text,
        btnText = R.string.confirm_btn_text,
        clickConfirm = clickConfirm
    )
}

fun Context.permissionPopUp() {
    customDialogTwoButton(
        R.string.go_setting,
        R.string.dialog_cancel,
        R.string.permission_title,
        R.string.permission_content,
        {
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.parse("package:" + this@permissionPopUp.packageName)
                startActivity(this)
            }
        })
}