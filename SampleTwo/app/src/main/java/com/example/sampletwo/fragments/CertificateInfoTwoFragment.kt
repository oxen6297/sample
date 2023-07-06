package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoTwoBinding
import com.example.sampletwo.viewmodels.MainViewModel


class CertificateInfoTwoFragment :
    BaseFragmentDataBinding<MainViewModel, FragmentCertificateInfoTwoBinding>(R.layout.fragment_certificate_info_two) {

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        setUpBinding()
    }

    private fun setUpBinding() {
        binding.apply {
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    R.id.action_certificationInfoTwoFragment_to_questionCertificationFragment
                )
            }
        }
    }
}