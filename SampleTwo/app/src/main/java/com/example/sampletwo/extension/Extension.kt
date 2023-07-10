package com.example.sampletwo.extension

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.visibilityGone() {
    visibility = View.GONE
}

fun View.visibilityVisible() {
    visibility = View.VISIBLE
}

fun Context.convertDpToPixel(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (density * dp).toInt()
}

fun Context.showToast(message: String, duration: Int = 0) {
    Toast.makeText(this, message, duration).show()
}