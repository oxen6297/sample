package com.example.sampletwo.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentMyInfoBinding
import com.example.sampletwo.datastore.model.UserInfo
import com.example.sampletwo.util.BitmapConverter
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyInfoFragment :
    BaseFragment<FragmentMyInfoBinding, DataStoreViewModel>(R.layout.fragment_my_info) {
    override val viewModel: DataStoreViewModel by viewModels()

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
        observeData()
        viewModel.readData()
        binding.apply {
            imgCopy.setOnClickListener {
                val clipBoard =
                    view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipBoard.setPrimaryClip(ClipData.newPlainText("did", textDidContent.text))
            }
        }
    }

    private fun observeData() {
        viewModel.userInfo.observe(viewLifecycleOwner, ::setData)
    }

    private fun setData(userInfo: UserInfo) {
        binding.apply {
            imgMy.setImageBitmap(BitmapConverter().stringToBitmap(userInfo.image))
            textName.text = userInfo.name
        }
    }
}