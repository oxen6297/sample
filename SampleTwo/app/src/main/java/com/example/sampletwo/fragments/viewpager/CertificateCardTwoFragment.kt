package com.example.sampletwo.fragments.viewpager

import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCertificateCardTwoBinding
import com.example.sampletwo.fragments.BaseFragment
import com.example.sampletwo.viewmodels.MainViewModel

class CertificateCardTwoFragment :
    BaseFragment<FragmentCertificateCardTwoBinding, MainViewModel>(R.layout.fragment_certificate_card_two) {

    override val viewModel: MainViewModel by viewModels()

    override fun setUpBinding(view: View) {

    }
}