package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentBioVerifyBinding
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BioVerifyFragment :
    BaseFragment<FragmentBioVerifyBinding, DataStoreViewModel>(R.layout.fragment_bio_verify) {

    override val viewModel: DataStoreViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.btnSkip.setOnClickListener {
            findNavController().navigate(BioVerifyFragmentDirections.actionBioVerifyFragmentToCompleteSettingFragment())
        }
    }
}