package com.example.sampletwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.databinding.PagingItemListBinding
import com.example.sampletwo.retrofit.model.ApiData

class PagingAdapter : PagingDataAdapter<ApiData.DataList, PagingAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: PagingItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PagingItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            data = getItem(position)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ApiData.DataList>() {
        override fun areItemsTheSame(oldItem: ApiData.DataList, newItem: ApiData.DataList): Boolean {
            return oldItem.excMan == newItem.excMan
        }

        override fun areContentsTheSame(oldItem: ApiData.DataList, newItem: ApiData.DataList): Boolean {
            return oldItem == newItem
        }
    }
}