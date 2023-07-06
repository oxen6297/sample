package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentQuestionCertificationBinding

class QuestionCertificationFragment :
    BaseFragment<FragmentQuestionCertificationBinding>(FragmentQuestionCertificationBinding::inflate) {

    private val args: QuestionCertificationFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textHowVerify.text = args.way
            appBar.imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnCancel.setOnClickListener {
                findNavController().navigate(R.id.action_questionCertificationFragment_to_certificateFragment)
            }
            btnNext.setOnClickListener {
                findNavController().navigate(R.id.action_questionCertificationFragment_to_completeVerifyFragment)
            }
        }
    }
}