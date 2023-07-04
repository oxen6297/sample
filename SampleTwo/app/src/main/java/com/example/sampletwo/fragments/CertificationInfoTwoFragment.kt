package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificationInfoTwoBinding


class CertificationInfoTwoFragment :
    BaseFragment<FragmentCertificationInfoTwoBinding>(FragmentCertificationInfoTwoBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            radioButtonVerifyMobile.isChecked = true
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setOnClickListener {
                findNavController().navigate(R.id.questionCertificationFragment)
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