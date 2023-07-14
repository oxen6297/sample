package com.example.sampletwo.fragments.viewpager

import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateCardOneBinding
import com.example.sampletwo.fragments.BaseFragment
import com.example.sampletwo.viewmodels.MainViewModel

class CertificateCardOneFragment :
    BaseFragment<FragmentCertificateCardOneBinding, MainViewModel>(R.layout.fragment_certificate_card_one) {

    override val viewModel: MainViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
    }
}