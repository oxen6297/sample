package com.example.sampletwo.util

import android.os.SystemClock
import android.view.View

class SingleClickListener(private val onSingleClick: (View) -> Unit) : View.OnClickListener {

    private val clickTerm = 1000L
    private var lastClick = 0L

    override fun onClick(v: View) {
        val elapsedRealtime = SystemClock.elapsedRealtime()
        if (elapsedRealtime - lastClick > clickTerm) {
            onSingleClick(v)
            lastClick = elapsedRealtime
        }
    }
}