package com.example.sampletwo.extension

import android.content.Context
import android.view.View
import com.example.sampletwo.R
import com.example.sampletwo.util.CustomDialog
import com.example.sampletwo.util.CustomDialogTwoButton

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Context.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (density * dp).toInt()
}

fun Context.customDialog(textViewText: Int, btnText: Int): CustomDialog {
    return CustomDialog(this, R.layout.custom_dialog).apply {
        initView()
        setViewComponent(R.id.btn_confirm, R.id.text_dialog_certificate)
        showDialog()
        textContent.text = getString(textViewText)
        confirmBtn.text = getString(btnText)
        confirmBtn()
    }
}

fun Context.customDialogTwoButton(
    confirmBtnText: Int,
    cancelBtnText: Int,
    titleText: Int,
    contentText: Int,
    clickConfirm: (View) -> Unit,
    clickCancel: (View) -> Unit = {},
): CustomDialogTwoButton {
    return CustomDialogTwoButton(this, R.layout.custom_dialog_two_button).apply {
        initView()
        setViewComponent(R.id.btn_confirm, R.id.btn_cancel, R.id.text_title, R.id.text_content)
        showDialog()
        confirmBtn.text = getString(confirmBtnText)
        cancelBtn.text = getString(cancelBtnText)
        textTitle.text = getString(titleText)
        textContent.text = getString(contentText)
        cancelBtn(clickCancel)
        confirmBtn(clickConfirm)
    }
}