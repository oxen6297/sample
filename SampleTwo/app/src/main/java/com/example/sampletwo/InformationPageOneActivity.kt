package com.example.sampletwo

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityInformationScreenOneBinding

class InformationPageOneActivity : BaseActivity<ActivityInformationScreenOneBinding>(
    ActivityInformationScreenOneBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            btnNext.setOnClickListener {
                nextBtnClickListener(InformationPageTwoActivity())
            }
            btnSkip.setOnClickListener {
                nextBtnClickListener(AgreementPageActivity())
            }
            appBar.imgBack.setOnClickListener {
                finish()
            }
        }
    }
}