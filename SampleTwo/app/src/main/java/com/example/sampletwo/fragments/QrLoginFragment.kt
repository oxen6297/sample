package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentQrLoginBinding
import com.example.sampletwo.viewmodels.MainViewModel


class QrLoginFragment :
    BaseFragment<FragmentQrLoginBinding, MainViewModel>(R.layout.fragment_qr_login) {

    override val viewModel: MainViewModel by viewModels()

    override fun setUpBinding(view: View) {

    }
}