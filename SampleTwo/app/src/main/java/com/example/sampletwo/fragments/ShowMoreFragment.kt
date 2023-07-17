package com.example.sampletwo.fragments

import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentShowMoreBinding
import com.example.sampletwo.datastore.UserInfo
import com.example.sampletwo.extension.customDialogTwoButton
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowMoreFragment :
    BaseFragment<FragmentShowMoreBinding, DataStoreViewModel>(R.layout.fragment_show_more) {

    override val viewModel: DataStoreViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallBack)
    }

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
        observeData()
        viewModel.readData()
        setUpBinding(view.context)
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
        viewModel.apply {
            viewModel.switchValue.observe(viewLifecycleOwner, ::agreeWatcher)
            viewModel.userInfo.observe(viewLifecycleOwner, ::userInfoWatcher)
        }
    }

    private fun userInfoWatcher(userInfo: UserInfo) {
        binding.apply {
            if (userInfo.certificateDate != 0L) {
                textGoCertificateContent.hide()
                layoutDid.show()
                layoutSetCardImage.show()
                layoutCertificateList.show()
                textGoCertificate.text = userInfo.name
                layoutGoCertificate.setOnClickListener {
                    findNavController().navigate(ShowMoreFragmentDirections.actionShowMoreFragmentToMyInfoFragment())
                }
            } else {
                layoutGoCertificate.setOnClickListener {
                    findNavController().navigate(ShowMoreFragmentDirections.actionShowMoreFragmentToCertificateInfoFragment())
                }
            }
        }
    }

    private fun agreeWatcher(onOff: Boolean) {
        binding.switchActivateNotification.isChecked = onOff
    }
}