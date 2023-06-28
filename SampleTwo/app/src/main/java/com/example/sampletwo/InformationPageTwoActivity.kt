package com.example.sampletwo

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityInformationScreenTwoBinding

class InformationPageTwoActivity : BaseActivity<ActivityInformationScreenTwoBinding>(
    ActivityInformationScreenTwoBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val informationPageThreeActivity = InformationPageThreeActivity()
        val agreementPageActivity = AgreementPageActivity()
        nextBtnClickListener(binding.btnNext, informationPageThreeActivity)
        nextBtnClickListener(binding.btnSkip, agreementPageActivity)
        backBtnClickListener(binding.appBar.imgBack)
    }
}