package com.example.sampletwo

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityInformationScreenTwoBinding

class InformationPageTwoActivity : BaseActivity<ActivityInformationScreenTwoBinding>(
    ActivityInformationScreenTwoBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            btnNext.setOnClickListener {
                nextBtnClickListener(InformationPageThreeActivity())
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