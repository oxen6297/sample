package com.example.sampletwo.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentShowMoreBinding
import com.example.sampletwo.util.CustomDialogTwoButton
import com.example.sampletwo.viewmodels.MainViewModel

class ShowMoreFragment : BaseFragment<FragmentShowMoreBinding>(FragmentShowMoreBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallBack)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setUpBinding(view.context)
    }

    private fun setUpBinding(context: Context) {
        val customDialogTwoButton = CustomDialogTwoButton(context, viewModel.switchValue)
        binding.apply {
            layoutActivateNotification.setOnClickListener {
                customDialogTwoButton.setDialog()
            }
            layoutGoCertificate.setOnClickListener {
                findNavController().navigate(R.id.action_showMoreFragment_to_certificateInfoFragment)
            }
        }
    }

    private fun observeData() {
        viewModel.switchValue.observe(viewLifecycleOwner, ::agreeWatcher)
    }

    private fun agreeWatcher(onOff: Boolean) {
        binding.switchActivateNotification.isChecked = onOff
    }
}