package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentCompleteVerifyBinding

class CompleteVerifyFragment :
    BaseFragment<FragmentCompleteVerifyBinding>(FragmentCompleteVerifyBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_completeVerifyFragment_to_certificateInfoThreeFragment)
        }
    }
}