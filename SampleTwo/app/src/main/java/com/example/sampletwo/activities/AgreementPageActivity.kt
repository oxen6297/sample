package com.example.sampletwo.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.example.sampletwo.MainViewModel
import com.example.sampletwo.R
import com.example.sampletwo.databinding.ActivityAgreementPageBinding
import com.example.sampletwo.extension.clickable

class AgreementPageActivity : BaseActivity<ActivityAgreementPageBinding>(
    ActivityAgreementPageBinding::inflate
) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        observeData()
        isAgree()
    }

    private fun setBinding() {
        binding.apply {
            appBar.imgBack.setOnClickListener {
                finish()
            }
            btnConfirm.setOnClickListener {
                nextBtnClickListener(MainScreenActivity())
            }
            layoutAgreeOne.setOnClickListener {
                mainViewModel.agreeOne.value = !(mainViewModel.agreeOne.value ?: false)
                mainViewModel.isAgreeAll()
            }
            layoutAgreeTwo.setOnClickListener {
                mainViewModel.agreeTwo.value = !(mainViewModel.agreeTwo.value ?: false)
                mainViewModel.isAgreeAll()
            }
            layoutAgreeThree.setOnClickListener {
                mainViewModel.agreeThree.value = !(mainViewModel.agreeThree.value ?: false)
                mainViewModel.isAgreeAll()
            }
            layoutAgreeFour.setOnClickListener {
                mainViewModel.agreeFour.value = !(mainViewModel.agreeFour.value ?: false)
                mainViewModel.isAgreeAll()
            }
            layoutAgreeAll.setOnClickListener {
                if (mainViewModel.agreeAll.value == true) {
                    mainViewModel.setAgreeAll(false)
                    mainViewModel.isAgreeAll()
                } else {
                    mainViewModel.setAgreeAll(true)
                    mainViewModel.isAgreeAll()
                }
            }
        }
    }

    private fun observeData() {
        mainViewModel.agreeOne.observe(this, ::agreeOneWatcher)
        mainViewModel.agreeTwo.observe(this, ::agreeTwoWatcher)
        mainViewModel.agreeThree.observe(this, ::agreeThreeWatcher)
        mainViewModel.agreeFour.observe(this, ::agreeFourWatcher)
    }

    private fun agreeAllWatcher(isChecked: Boolean) {
        binding.checkboxAgreeAll.isChecked = isChecked
    }

    private fun agreeOneWatcher(isChecked: Boolean) {
        binding.checkboxAgreeOne.isChecked = isChecked
    }

    private fun agreeTwoWatcher(isChecked: Boolean) {
        binding.checkboxAgreeTwo.isChecked = isChecked
    }

    private fun agreeThreeWatcher(isChecked: Boolean) {
        binding.checkboxAgreeThree.isChecked = isChecked
    }

    private fun agreeFourWatcher(isChecked: Boolean) {
        binding.checkboxAgreeFour.isChecked = isChecked
    }

    private fun isAgree() {
        mainViewModel.agreeAll.observe(this) { agree ->
            agreeAllWatcher(agree)
            if (agree) {
                binding.btnConfirm.clickable(
                    R.drawable.confirm_button_background,
                    getColor(R.color.white),
                    true
                )
            } else {
                binding.btnConfirm.clickable(
                    R.drawable.disabled_btn_background,
                    getColor(R.color.disabled_btn_text_color),
                    false
                )
            }
        }
    }
}