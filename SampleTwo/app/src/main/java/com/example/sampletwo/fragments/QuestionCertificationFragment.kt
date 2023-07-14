package com.example.sampletwo.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentQuestionCertificationBinding
import com.example.sampletwo.viewmodels.MainViewModel

class QuestionCertificationFragment :
    BaseFragmentDataBinding<FragmentQuestionCertificationBinding, MainViewModel>(R.layout.fragment_question_certification) {

    override val viewModel: MainViewModel by viewModels()

    private val args: QuestionCertificationFragmentArgs by navArgs()

    override fun setUpBinding(view: View) {
        binding.apply {
            vm = viewModel
            textHowVerify.text = args.way
        }
    }
}