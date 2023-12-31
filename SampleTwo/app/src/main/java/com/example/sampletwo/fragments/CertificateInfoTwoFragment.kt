package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateInfoTwoBinding
import com.example.sampletwo.viewmodels.MainViewModel


class CertificateInfoTwoFragment :
    BaseFragment<FragmentCertificateInfoTwoBinding, MainViewModel>(R.layout.fragment_certificate_info_two) {

    override val viewModel: MainViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
    }
}