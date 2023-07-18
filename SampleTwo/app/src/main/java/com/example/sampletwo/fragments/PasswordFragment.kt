package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentPasswordBinding
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment :
    BaseFragment<FragmentPasswordBinding, DataStoreViewModel>(R.layout.fragment_password) {

    override val viewModel: DataStoreViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
        observePasswordData()
    }

    private fun observePasswordData() = viewModel.passwordList.observe(viewLifecycleOwner, ::passwordWatcher)


    private fun passwordWatcher(password: MutableList<Int?>) {
        binding.apply {

        }
    }
}