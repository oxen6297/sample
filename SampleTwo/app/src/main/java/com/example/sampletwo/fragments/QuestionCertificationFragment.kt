package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentQuestionCertificationBinding

class QuestionCertificationFragment :
    BaseFragment<FragmentQuestionCertificationBinding>(FragmentQuestionCertificationBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnCancel.setOnClickListener {
                findNavController().navigate(R.id.certificateFragment)
            }
            btnNext.setOnClickListener {
                findNavController().navigate(R.id.completeVerifyFragment)
            }
        }
    }
}