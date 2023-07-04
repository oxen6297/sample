package com.example.sampletwo.util

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.RelativeSizeSpan

class RelativeSizeTypeSpan(
    size: Float,
    private val colorResource: Int,
    private val textType: Int
) : RelativeSizeSpan(size) {
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = colorResource
        ds.typeface = Typeface.create(Typeface.DEFAULT, textType)
    }
}