package com.example.sampletwo.extension

import android.content.Context
import android.view.View
import com.example.sampletwo.R
import com.example.sampletwo.util.CustomDialog
import com.example.sampletwo.util.CustomDialogTwoButton
import com.example.sampletwo.util.SingleClickListener

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.isShow(): Boolean {
    return visibility == View.VISIBLE
}

fun Context.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (density * dp).toInt()
}

fun Context.customDialog(
    textViewText: Int,
    btnText: Int,
    clickConfirm: (View) -> Unit = {}
): CustomDialog {
    return CustomDialog(this, R.layout.custom_dialog).apply {
        initView()
        setViewComponent(R.id.btn_confirm, R.id.text_dialog_certificate)
        setViewText(textViewText, btnText)
        confirmBtn(clickConfirm)
        showDialog()
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
        setViewText(confirmBtnText, cancelBtnText, titleText, contentText)
        cancelBtn(clickCancel)
        confirmBtn(clickConfirm)
        showDialog()
    }
}

fun View.singleClickListener(onSingleClick: (View) -> Unit) {
    setOnClickListener(SingleClickListener(onSingleClick))
}