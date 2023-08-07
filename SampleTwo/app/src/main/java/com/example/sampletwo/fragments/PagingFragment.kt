package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sampletwo.R
import com.example.sampletwo.adapter.LoadStateAdapter
import com.example.sampletwo.adapter.PagingAdapter
import com.example.sampletwo.databinding.FragmentPagingBinding
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.retrofit.handleData
import com.example.sampletwo.viewmodels.RetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingFragment :
    BaseFragment<FragmentPagingBinding, RetrofitViewModel>(R.layout.fragment_paging) {

    override val viewModel: RetrofitViewModel by viewModels()
    private val pagingAdapter = PagingAdapter()

    override fun setUpBinding(view: View) {
        binding.recyclerviewPaging.adapter = pagingAdapter.withLoadStateFooter(LoadStateAdapter())
        observeData(view.context)
        viewModel.getData()
        getTotalCnt()
        viewModel.getTotalCnt()
    }

    private fun observeData(context: Context) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect {
                    handleData(binding.progressbar, context, it) { data ->
                        pagingAdapter.submitData(lifecycle, data)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getTotalCnt() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.totalCnt.collect {
                    binding.apply {
                        if (it == null) textTotalCount.hide()
                        else {
                            textTotalCount.show()
                            binding.textTotalCount.text = "totalCount: $it"
                        }
                    }
                }
            }
        }
    }
}