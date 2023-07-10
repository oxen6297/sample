package com.example.sampletwo.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentSignUpBinding
import com.example.sampletwo.extension.showToast
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    BaseFragmentDataBinding<DataStoreViewModel, FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    override val viewModel: DataStoreViewModel by viewModels()
    private val signUpFragmentArgs: SignUpFragmentArgs by navArgs()

    override fun setUpBinding(context: Context) {
        viewModel.signUpImage.value = signUpFragmentArgs.image
        binding.vm = viewModel
        binding.btnConfirm.setOnClickListener {
            if (checkBlank(context)) {
                viewModel.saveData()
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToCertificateFragment())
            }
        }
    }

    private fun checkBlank(context: Context): Boolean {
        viewModel.apply {
            return if (signUpName.value.isNullOrBlank()) {
                context.showToast("이름을 입력해주세요.")
                false
            } else if (signUpCertificationNumber.value.isNullOrBlank()) {
                context.showToast("연금증서번호를 입력해주세요.")
                false
            } else if (signUpBirth.value.isNullOrBlank()) {
                context.showToast("생년월일을 입력해주세요.")
                false
            } else if (signUpBirth.value.toString().length < 8) {
                context.showToast("생년월일을 8자리로 입력해주세요.")
                false
            } else if (signUpRecentCompany.value.isNullOrBlank()) {
                context.showToast("최종 근무지를 입력해주세요.")
                false
            } else return true
        }
    }
}