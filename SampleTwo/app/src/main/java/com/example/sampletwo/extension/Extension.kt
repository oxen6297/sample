package com.example.sampletwo.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.sampletwo.R
import com.example.sampletwo.util.CustomDialog
import com.example.sampletwo.util.CustomDialogTwoButton
import com.example.sampletwo.util.SingleClickListener
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken

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

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun SearchView.queryTextListener(search: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            search(query.toString())
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}

inline fun <reified T> JsonElement.toList(): List<T> =
    Gson().fromJson(this, object : TypeToken<List<T>>() {}.type)