package com.example.sampletwo.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentShowMoreBinding
import com.example.sampletwo.datastore.model.UserInfo
import com.example.sampletwo.extension.customDialogTwoButton
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.util.CipherUtil
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class ShowMoreFragment :
    BaseFragment<FragmentShowMoreBinding, DataStoreViewModel>(R.layout.fragment_show_more) {

    override val viewModel: DataStoreViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
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
            layoutNotice.setOnClickListener {
                findNavController().navigate(ShowMoreFragmentDirections.actionShowMoreFragmentToNoticeFragment())
            }
            layoutMap.setOnClickListener {
                findNavController().navigate(ShowMoreFragmentDirections.actionShowMoreFragmentToMapFragment())
            }
            layoutSetPassword.setOnClickListener {
                findNavController().navigate(ShowMoreFragmentDirections.actionShowMoreFragmentToPasswordFragment())
            }
            layoutGoCertificate.setOnClickListener {
                if (viewModel.userInfo.value == null) {
                    findNavController().navigate(ShowMoreFragmentDirections.actionShowMoreFragmentToCertificateInfoFragment())
                } else findNavController().navigate(ShowMoreFragmentDirections.actionShowMoreFragmentToMyInfoFragment())
            }
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
            layoutScheme.setOnClickListener {
                val encryptText = CipherUtil(context).encrypt("testText")
                val urlScheme = "sample://samplehost?text=$encryptText"
                Intent().apply {
                    action = Intent.ACTION_VIEW
                    addCategory(Intent.CATEGORY_BROWSABLE)
                    addCategory(Intent.CATEGORY_DEFAULT)
                    data = Uri.parse(urlScheme)
                    startActivity(this)
                }
            }
        }
    }

    private fun observeData() {
        viewModel.apply {
            switchValue.observe(viewLifecycleOwner, ::agreeWatcher)
            userInfo.observe(viewLifecycleOwner, ::userInfoWatcher)
        }
    }

    private fun userInfoWatcher(userInfo: UserInfo?) {
        binding.apply {
            if (userInfo != null) {
                textGoCertificateContent.hide()
                layoutDid.show()
                layoutSetCardImage.show()
                layoutCertificateList.show()
                textGoCertificate.text = userInfo.name
            }
        }
    }

    private fun agreeWatcher(onOff: Boolean) {
        binding.switchActivateNotification.isChecked = onOff
    }
}