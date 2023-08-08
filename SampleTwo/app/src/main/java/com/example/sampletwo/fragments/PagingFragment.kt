package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampletwo.R
import com.example.sampletwo.adapter.LoadStateAdapter
import com.example.sampletwo.adapter.PagingAdapter
import com.example.sampletwo.databinding.FragmentPagingBinding
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.repeatOnStarted
import com.example.sampletwo.extension.show
import com.example.sampletwo.retrofit.handleData
import com.example.sampletwo.viewmodels.RetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagingFragment :
    BaseFragment<FragmentPagingBinding, RetrofitViewModel>(R.layout.fragment_paging) {

    override val viewModel: RetrofitViewModel by viewModels()
    private val pagingAdapter = PagingAdapter()

    override fun setUpBinding(view: View) {
        binding.recyclerviewPaging.adapter =
            pagingAdapter.withLoadStateFooter(LoadStateAdapter(pagingAdapter))
        collectData(view.context)
        collectTotalCnt()
        viewModel.apply {
            getData()
            getTotalCnt()
        }
    }

    private fun collectData(context: Context) {
        repeatOnStarted {
            viewModel.data.collect {
                handleData(binding.progressbar, context, it) { data ->
                    pagingAdapter.submitData(lifecycle, data)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun collectTotalCnt() {
        repeatOnStarted {
            viewModel.totalCnt.collect { totalCount ->
                binding.textTotalCount.apply {
                    if (totalCount == null) hide()
                    else {
                        show()
                        text = "totalCount: $totalCount"
                    }
                }
            }
        }
    }
}