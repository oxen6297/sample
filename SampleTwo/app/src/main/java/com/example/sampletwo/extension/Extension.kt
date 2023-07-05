package com.example.sampletwo.extension

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.View
import android.widget.TextView
import com.example.sampletwo.util.IndentLeadingMarginSpan
import com.example.sampletwo.util.RelativeSizeTypeSpan

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

fun TextView.spannableIndentMarginBuilder(marginText: String) {
    val textData = text
    val builder = SpannableStringBuilder(textData)
    builder.setSpan(
        IndentLeadingMarginSpan(marginText),
        0,
        textData.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = builder
}