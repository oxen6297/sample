package com.example.sampletwo.extension

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.widget.Button
import android.widget.TextView
import com.example.sampletwo.util.RelativeSizeTypeSpan

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

fun TextView.spannableStringBuilder(
    colorResource: Int,
    startIndex: Int,
    endIndex: Int,
    typeResource: Int = Typeface.BOLD,
    textSize: Float = 1f
) {
    val textData = text
    val builder = SpannableStringBuilder(textData)
    builder.setSpan(
        RelativeSizeTypeSpan(textSize, colorResource, typeResource),
        startIndex,
        endIndex,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = builder
}