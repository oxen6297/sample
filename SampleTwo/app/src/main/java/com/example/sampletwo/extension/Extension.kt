package com.example.sampletwo.extension

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