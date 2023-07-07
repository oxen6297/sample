package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoThreeBinding
import com.example.sampletwo.util.IndentLeadingMarginSpan

class CertificateInfoThreeFragment :
    BaseFragment<FragmentCertificateInfoThreeBinding>(FragmentCertificateInfoThreeBinding::inflate) {
    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val blueColor = view.context.getColor(R.color.confirm_btn_color)
        val redColor = view.context.getColor(R.color.red)

        binding.apply {
            textInfoCameraIdCardContent.run {
                val builder = SpannableStringBuilder(text).apply {
                    setSpan(ForegroundColorSpan(blueColor), 29, 35, 0)
                    setSpan(ForegroundColorSpan(blueColor), 38, 44, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 29, 35, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 38, 44, 0)
                }
                text = builder
            }
            textCaution.run {
                val builder = SpannableStringBuilder(text).apply {
                    setSpan(ForegroundColorSpan(redColor), 0, 2, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 0, 2, 0)
                }
                text = builder
            }
            textCautionContent.run {
                val builder = SpannableStringBuilder(text).apply {
                    setSpan(ForegroundColorSpan(blueColor), 1, 7, 0)
                    setSpan(ForegroundColorSpan(blueColor), 30, 37, 0)
                    setSpan(ForegroundColorSpan(blueColor), 39, 46, 0)
                    setSpan(ForegroundColorSpan(blueColor), 58, 64, 0)
                    setSpan(ForegroundColorSpan(blueColor), 80, 86, 0)
                    setSpan(ForegroundColorSpan(blueColor), 115, 122, 0)
                    setSpan(ForegroundColorSpan(blueColor), 155, 162, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 1, 7, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 30, 37, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 39, 46, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 58, 64, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 80, 86, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 115, 122, 0)
                    setSpan(StyleSpan(Typeface.BOLD), 155, 162, 0)
                    setSpan(IndentLeadingMarginSpan("· "), 0, text.length, 0)
                }
                text = builder
            }
        }
    }
}