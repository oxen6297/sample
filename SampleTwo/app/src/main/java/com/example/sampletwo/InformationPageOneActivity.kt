package com.example.sampletwo

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityInformationScreenOneBinding

class InformationPageOneActivity : BaseActivity<ActivityInformationScreenOneBinding>(
    ActivityInformationScreenOneBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val informationPageTwoActivity = InformationPageTwoActivity()
        val agreementPageActivity = AgreementPageActivity()
        nextBtnClickListener(binding.btnNext, informationPageTwoActivity)
        nextBtnClickListener(binding.btnSkip, agreementPageActivity)
        backBtnClickListener(binding.appBar.imgBack)
    }
}