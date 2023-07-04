package com.example.sampletwo.util

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.style.LeadingMarginSpan

class IndentLeadingMarginSpan(private val marginText:String) : LeadingMarginSpan {

    private var indentMargin: Int = 0

    override fun drawLeadingMargin(
        canvas: Canvas, paint: Paint, currentMarginLocation: Int, paragraphDirection: Int,
        lineTop: Int, lineBaseline: Int, lineBottom: Int, text: CharSequence, lineStart: Int,
        lineEnd: Int, isFirstLine: Boolean, layout: Layout
    ) {
        val lineFirstText = text.substring(lineStart, lineStart + 1)
        indentMargin =
            if (INDENT_MARGIN_STRING.contains(lineFirstText)) 0
            else paint.measureText(marginText).toInt()
    }

    override fun getLeadingMargin(first: Boolean): Int = indentMargin

    companion object {
        private val INDENT_MARGIN_STRING = listOf("·", "※")
    }
}