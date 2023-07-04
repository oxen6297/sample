package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoBinding

class CertificateInfoFragment :
    BaseFragment<FragmentCertificateInfoBinding>(FragmentCertificateInfoBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    R.id.action_certificateInfoFragment_to_certificationInfoTwoFragment
                )
            }
        }
    }
}