package com.example.sampletwo

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sampletwo.databinding.ActivityAgreementPageBinding

class AgreementPageActivity : BaseActivity<ActivityAgreementPageBinding>(
    ActivityAgreementPageBinding::inflate
) {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        backBtnClickListener(binding.appBar.imgBack)
        clickCheckBox()
        isAgree()
    }

    private fun clickCheckBox() {
        binding.checkboxAgreeAll.setOnCheckedChangeListener { _, isChecked ->
            setCheckbox(isChecked)
            mainViewModel.isAgree.value = isChecked
            mainViewModel.setValueIsAgree()
        }
        binding.checkboxAgreeService.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.agreeOne.value = isChecked
            mainViewModel.setValueIsAgree()
        }
        binding.checkboxAgreeIndividual.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.agreeTwo.value = isChecked
            mainViewModel.setValueIsAgree()
        }
        binding.checkboxAgreeIndividualTwo.setOnCheckedChangeListener { _, isChecked ->
            mainViewModel.agreeThree.value = isChecked
            mainViewModel.setValueIsAgree()
        }
    }


    private fun confirmBtnClickable(resource: Int, colorResource: Int, isClickable: Boolean) {
        binding.btnConfirm.run {
            this.setBackgroundResource(resource)
            this.setTextColor(colorResource)
            this.isClickable = isClickable
        }
    }

    private fun setCheckbox(isChecked: Boolean) {
        binding.checkboxAgreeService.isChecked = isChecked
        binding.checkboxAgreeIndividual.isChecked = isChecked
        binding.checkboxAgreeIndividualTwo.isChecked = isChecked
        binding.checkboxAgreeNotification.isChecked = isChecked
    }

    private fun isAgree() {
        mainViewModel.isAgree.observe(this){ agree ->
            if (agree){
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