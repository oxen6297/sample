package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sampletwo.R
import com.example.sampletwo.adapter.PagingAdapter
import com.example.sampletwo.databinding.FragmentPagingBinding
import com.example.sampletwo.retrofit.handleData
import com.example.sampletwo.viewmodels.RetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingFragment :
    BaseFragment<FragmentPagingBinding, RetrofitViewModel>(R.layout.fragment_paging) {

    override val viewModel: RetrofitViewModel by viewModels()
    private val pagingAdapter = PagingAdapter()

    override fun setUpBinding(view: View) {
        binding.recyclerviewPaging.adapter = pagingAdapter
        observeData(view.context)
        viewModel.getData()
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(context: Context) {
        lifecycleScope.launch {
            viewModel.totalCnt.observe(viewLifecycleOwner) {
                binding.textTotalCount.text = "totalCount: $it"
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collectLatest {
                    handleData(binding.progressbar, context, it) { data ->
                        pagingAdapter.submitData(lifecycle, data)
                    }
                }
            }
        }
    }
}