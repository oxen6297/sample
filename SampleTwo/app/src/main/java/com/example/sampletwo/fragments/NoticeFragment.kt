package com.example.sampletwo.fragments

import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.adapter.NoticeAdapter
import com.example.sampletwo.databinding.FragmentNoticeBinding
import com.example.sampletwo.room.NoticeEntity
import com.example.sampletwo.viewmodels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoticeFragment :
    BaseFragment<FragmentNoticeBinding, RoomViewModel>(R.layout.fragment_notice) {

    override val viewModel: RoomViewModel by viewModels()
    private val noticeAdapter = NoticeAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallBack)
    }

    override fun setUpBinding(view: View) {
        binding.recyclerviewNotice.adapter = noticeAdapter
        observeData()
        viewModel.getNotice()
        binding.appBar.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.noticeData.observe(viewLifecycleOwner, ::noticeWatcher)
        }
    }

    private fun noticeWatcher(listNotice: List<NoticeEntity>) = noticeAdapter.submitList(listNotice)
}