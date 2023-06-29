package com.example.sampletwo.activities

import android.os.Bundle
import com.example.sampletwo.databinding.ActivityAccessAuthorizationBinding

class AccessAuthorizationActivity :
    BaseActivity<ActivityAccessAuthorizationBinding>(ActivityAccessAuthorizationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnConfirm.setOnClickListener {
            nextBtnClickListener(InformationPageOneActivity())
        }
    }
}