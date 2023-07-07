package com.example.sampletwo.extension

import android.content.Context
import android.view.View

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