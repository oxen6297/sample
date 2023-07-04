package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateCardOneBinding

class CertificateCardOneFragment :
    BaseFragment<FragmentCertificateCardOneBinding>(FragmentCertificateCardOneBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetIssue.setOnClickListener {
            findNavController().navigate(R.id.action_certificateFragment_to_certificateInfoFragment)
        }
    }
}