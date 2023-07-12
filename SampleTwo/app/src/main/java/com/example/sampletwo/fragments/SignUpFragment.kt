package com.example.sampletwo.fragments

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentSignUpBinding
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragmentDataBinding<FragmentSignUpBinding>(R.layout.fragment_sign_up) {

    private val viewModel: DataStoreViewModel by viewModels()
    private val signUpFragmentArgs: SignUpFragmentArgs by navArgs()

    override fun setUpBinding(context: Context) {
        viewModel.signUpImage.value = signUpFragmentArgs.image
        binding.apply {
            vm = viewModel
            btnConfirm.setOnClickListener {
                viewModel.setIsBlank()
                observeBlankData()
            }
        }
    }

    private fun observeBlankData() =
        viewModel.isBlank.observe(viewLifecycleOwner, ::confirmBtnClick)


    private fun confirmBtnClick(clickable: Boolean) {
        if (clickable) {
            binding.btnConfirm.isClickable = true
            viewModel.saveData()
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToCertificateFragment())
        } else binding.btnConfirm.isClickable = false
    }
}