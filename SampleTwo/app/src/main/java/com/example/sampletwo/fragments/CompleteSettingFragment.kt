package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCompleteSettingBinding
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompleteSettingFragment :
    BaseFragment<FragmentCompleteSettingBinding, DataStoreViewModel>(R.layout.fragment_complete_setting) {

    override val viewModel: DataStoreViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(CompleteSettingFragmentDirections.actionCompleteSettingFragmentToShowMoreFragment())
        }
    }
}