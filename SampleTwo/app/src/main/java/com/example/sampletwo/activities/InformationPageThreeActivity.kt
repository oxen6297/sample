package com.example.sampletwo.activities

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityInformationScreenThreeBinding

class InformationPageThreeActivity : BaseActivity<ActivityInformationScreenThreeBinding>(
    ActivityInformationScreenThreeBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            btnConfirm.setOnClickListener {
                nextBtnClickListener(AgreementPageActivity())
            }
            appBar.imgBack.setOnClickListener {
                finish()
            }
        }
    }
}