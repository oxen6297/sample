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
    private lateinit var wayToVerify: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        observeData()
        setUpBinding()
    }

    private fun setUpBinding() {
        binding.apply {
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    CertificateInfoTwoFragmentDirections.actionCertificationInfoTwoFragmentToQuestionCertificationFragment(wayToVerify)
                )
            }
        }
    }

    private fun observeData() {
        viewModel.radioButtonClick.observe(viewLifecycleOwner, ::wayToVerifyWatcher)
    }

    private fun wayToVerifyWatcher(type: MainViewModel.RadioType) {
        wayToVerify = type.name
    }
}