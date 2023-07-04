package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoTwoBinding


class CertificateInfoTwoFragment :
    BaseFragment<FragmentCertificateInfoTwoBinding>(FragmentCertificateInfoTwoBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            radioButtonVerifyMobile.isChecked = true
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    R.id.action_certificationInfoTwoFragment_to_questionCertificationFragment
                )
            }
            layoutPossibleVerifyMobile.setOnClickListener {
                radioButtonVerifyMobile.isChecked = true
                radioButtonVerifyCertification.isChecked = false
            }
            layoutPossibleOnlyOne.setOnClickListener {
                radioButtonVerifyMobile.isChecked = false
                radioButtonVerifyCertification.isChecked = true
            }
        }
    }
}