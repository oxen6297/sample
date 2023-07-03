package com.example.sampletwo.extension

import android.content.Context
import android.widget.Button

fun Button.clickable(
    resource: Int,
    colorResource: Int,
    isClickable: Boolean
) {
    this.setBackgroundResource(resource)
    this.setTextColor(colorResource)
    this.isClickable = isClickable
}

fun Context.convertDpToPixel(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (density * dp).toInt()
}