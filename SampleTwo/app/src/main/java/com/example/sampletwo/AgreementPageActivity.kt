package com.example.sampletwo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.sampletwo.databinding.ActivityAgreementPageBinding

class AgreementPageActivity : BaseActivity<ActivityAgreementPageBinding>(
    ActivityAgreementPageBinding::inflate
) {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.appBar.imgBack.setOnClickListener {
            finish()
        }
        binding.btnConfirm.setOnClickListener {
            Log.d("click", "true")
        }
        clickCheckBox()
        observeData()
        isAgree()
    }

    private fun clickCheckBox() {
        binding.run {
            textAgreeOne.setOnClickListener {
                mainViewModel.agreeOne.value = !(mainViewModel.agreeOne.value ?: false)
                mainViewModel.isAgreeAll()
            }
            textAgreeTwo.setOnClickListener {
                mainViewModel.agreeTwo.value = !(mainViewModel.agreeTwo.value ?: false)
                mainViewModel.isAgreeAll()
            }
            textAgreeThree.setOnClickListener {
                mainViewModel.agreeThree.value = !(mainViewModel.agreeThree.value ?: false)
                mainViewModel.isAgreeAll()
            }
            textAgreeFour.setOnClickListener {
                mainViewModel.agreeFour.value = !(mainViewModel.agreeFour.value ?: false)
                mainViewModel.isAgreeAll()
            }
            textAgreeAll.setOnClickListener {
                if (mainViewModel.agreeAll.value == true) {
                    mainViewModel.setAgreeAll(false)
                    mainViewModel.isAgreeAll()
                } else {
                    mainViewModel.setAgreeAll(true)
                    mainViewModel.isAgreeAll()
                }
            }
            checkboxAgreeOne.setOnClickListener {
                mainViewModel.agreeOne.value = !(mainViewModel.agreeOne.value ?: false)
                mainViewModel.isAgreeAll()
            }
            checkboxAgreeTwo.setOnClickListener {
                mainViewModel.agreeTwo.value = !(mainViewModel.agreeTwo.value ?: false)
                mainViewModel.isAgreeAll()
            }
            checkboxAgreeThree.setOnClickListener {
                mainViewModel.agreeThree.value = !(mainViewModel.agreeThree.value ?: false)
                mainViewModel.isAgreeAll()
            }
            checkboxAgreeFour.setOnClickListener {
                mainViewModel.agreeFour.value = !(mainViewModel.agreeFour.value ?: false)
                mainViewModel.isAgreeAll()
            }
            checkboxAgreeAll.setOnClickListener {
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
        mainViewModel.agreeAll.observe(this) {
            binding.checkboxAgreeAll.isChecked = it
        }
        mainViewModel.agreeOne.observe(this) {
            binding.checkboxAgreeOne.isChecked = it
        }
        mainViewModel.agreeTwo.observe(this) {
            binding.checkboxAgreeTwo.isChecked = it
        }
        mainViewModel.agreeThree.observe(this) {
            binding.checkboxAgreeThree.isChecked = it
        }
        mainViewModel.agreeFour.observe(this) {
            binding.checkboxAgreeFour.isChecked = it
        }
    }

    private fun confirmBtnClickable(resource: Int, colorResource: Int, isClickable: Boolean) {
        binding.btnConfirm.run {
            this.setBackgroundResource(resource)
            this.setTextColor(colorResource)
            this.isClickable = isClickable
        }
    }

    private fun isAgree() {
        mainViewModel.agreeAll.observe(this) { agree ->
            if (agree) {
                confirmBtnClickable(
                    R.drawable.confirm_button_background,
                    Color.WHITE,
                    true
                )
            } else {
                confirmBtnClickable(
                    R.drawable.disabled_btn_background,
                    R.color.disabled_btn_text_color,
                    false
                )
            }
        }
    }
}