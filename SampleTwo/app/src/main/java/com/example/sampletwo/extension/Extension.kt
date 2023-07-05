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
    val builder = SpannableStringBuilder(text)
    builder.setSpan(
        RelativeSizeTypeSpan(textSize, colorResource, typeResource),
        startIndex,
        endIndex,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = builder
}

fun TextView.spannableIndentMarginBuilder(marginText: String) {
    val builder = SpannableStringBuilder(text)
    builder.setSpan(
        IndentLeadingMarginSpan(marginText),
        0,
        text.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = builder
}