package com.example.sampletwo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.databinding.PagingItemListBinding
import com.example.sampletwo.retrofit.model.NorthData

class PagingAdapter : PagingDataAdapter<NorthData, PagingAdapter.ViewHolder>(DiffCallback()) {

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

    class DiffCallback : DiffUtil.ItemCallback<NorthData>() {
        override fun areItemsTheSame(oldItem: NorthData, newItem: NorthData): Boolean {
            return oldItem.excMan == newItem.excMan
        }

        override fun areContentsTheSame(oldItem: NorthData, newItem: NorthData): Boolean {
            return oldItem == newItem
        }
    }
}