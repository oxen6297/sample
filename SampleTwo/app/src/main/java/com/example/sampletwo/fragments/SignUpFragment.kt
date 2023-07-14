package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentSignUpBinding
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, DataStoreViewModel>(R.layout.fragment_sign_up) {

    override val viewModel: DataStoreViewModel by viewModels()
    private val signUpFragmentArgs: SignUpFragmentArgs by navArgs()

    override fun setUpBinding(view: View) {
        viewModel.apply {
            signUpImage.value = signUpFragmentArgs.image
            binding.vm = this
        }
    }
}