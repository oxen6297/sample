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
                findNavController().navigate(R.id.certificateInfoFragment, null, backNavOptions)
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    R.id.questionCertificationFragment,
                    null,
                    nextNavOptions
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