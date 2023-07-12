package com.example.sampletwo.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoTwoBinding
import com.example.sampletwo.viewmodels.MainViewModel


class CertificateInfoTwoFragment :
    BaseFragmentDataBinding<MainViewModel, FragmentCertificateInfoTwoBinding>(R.layout.fragment_certificate_info_two) {

    override val viewModel: MainViewModel by viewModels()

    override fun setUpBinding(context: Context) {
        binding.vm = viewModel
        binding.apply {
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    CertificateInfoTwoFragmentDirections.actionCertificationInfoTwoFragmentToQuestionCertificationFragment(
                        viewModel.radioButtonClick.value?.name.toString()
                    )
                )
            }
        }
    }
}