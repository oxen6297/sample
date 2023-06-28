package com.example.sampletwo

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityInformationScreenThreeBinding

class InformationPageThreeActivity : BaseActivity<ActivityInformationScreenThreeBinding>(
    ActivityInformationScreenThreeBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val agreementPageActivity = AgreementPageActivity()
        nextBtnClickListener(binding.btnConfirm, agreementPageActivity)
        backBtnClickListener(binding.appBar.imgBack)
    }
}