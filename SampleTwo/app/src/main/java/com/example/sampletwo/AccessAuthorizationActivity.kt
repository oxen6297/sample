package com.example.sampletwo

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityAccessAuthorizationBinding

class AccessAuthorizationActivity :
    BaseActivity<ActivityAccessAuthorizationBinding>(ActivityAccessAuthorizationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val informationPageOneActivity = InformationPageOneActivity()
        nextBtnClickListener(binding.btnConfirm, informationPageOneActivity)
    }
}