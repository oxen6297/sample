package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCompleteVerifyBinding
import com.example.sampletwo.viewmodels.MainViewModel

class CompleteVerifyFragment :
    BaseFragmentDataBinding<FragmentCompleteVerifyBinding, MainViewModel>(R.layout.fragment_complete_verify) {

    override val viewModel: MainViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
    }
}