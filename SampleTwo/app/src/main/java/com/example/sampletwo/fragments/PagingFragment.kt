package com.example.sampletwo.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
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
        viewModel.getData()
        observeData(view.context)
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(context: Context) {
        viewModel.viewModelScope.launch {
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