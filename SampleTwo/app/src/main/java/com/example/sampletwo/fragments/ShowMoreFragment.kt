package com.example.sampletwo.fragments

import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentShowMoreBinding
import com.example.sampletwo.extension.customDialogTwoButton
import com.example.sampletwo.viewmodels.MainViewModel

class ShowMoreFragment : BaseFragment<FragmentShowMoreBinding,MainViewModel>(R.layout.fragment_show_more) {

    override val viewModel: MainViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
        observeData()
        setUpBinding(view.context)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallBack)
    }

    private fun setUpBinding(context: Context) {
        binding.apply {
            layoutActivateNotification.setOnClickListener {
                context.customDialogTwoButton(
                    R.string.agree,
                    R.string.not_agree,
                    R.string.agree_push,
                    R.string.agree_push_content,
                    {
                        viewModel.switchValue.value = true
                    }
                ) {
                    viewModel.switchValue.value = false
                }
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