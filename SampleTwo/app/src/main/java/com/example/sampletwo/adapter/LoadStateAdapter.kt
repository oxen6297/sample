package com.example.sampletwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.databinding.LoadStateItemBinding
import com.example.sampletwo.extension.showToast
import com.example.sampletwo.extension.singleClickListener

class LoadStateAdapter(private val adapter: PagingAdapter) :
    androidx.paging.LoadStateAdapter<LoadStateAdapter.ViewHolder>() {

    class ViewHolder(val binding: LoadStateItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.binding.apply {
            btnRetry.apply {
                isVisible = loadState is LoadState.Error
                singleClickListener { adapter.retry() }
            }
            progressbar.isVisible = loadState is LoadState.Loading
            if (loadState is LoadState.Error) root.context.showToast("에러가 발생하였습니다.")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = LoadStateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
}