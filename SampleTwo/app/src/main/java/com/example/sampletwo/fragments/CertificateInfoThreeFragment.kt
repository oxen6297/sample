package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoThreeBinding
import com.example.sampletwo.extension.spannableIndentMarginBuilder
import com.example.sampletwo.extension.spannableStringBuilder

class CertificateInfoThreeFragment :
    BaseFragment<FragmentCertificateInfoThreeBinding>(FragmentCertificateInfoThreeBinding::inflate) {

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val blueColor = view.context.getColor(R.color.confirm_btn_color)
        val redColor = view.context.getColor(R.color.red)

        binding.apply {
            textInfoCameraIdCardContent.apply {
                spannableStringBuilder(blueColor, 29, 35)
                spannableStringBuilder(blueColor, 38, 44)
            }
            textCautionContent.apply {
                spannableStringBuilder(blueColor, 1, 7)
                spannableStringBuilder(blueColor, 30, 37)
                spannableStringBuilder(blueColor, 39, 46)
                spannableStringBuilder(blueColor, 58, 64)
                spannableStringBuilder(blueColor, 80, 86)
                spannableStringBuilder(blueColor, 115, 122)
                spannableStringBuilder(blueColor, 155, 162)
                spannableIndentMarginBuilder("· ")
            }
            textCaution.spannableStringBuilder(redColor, 0, 2, Typeface.NORMAL)
        }
    }
}