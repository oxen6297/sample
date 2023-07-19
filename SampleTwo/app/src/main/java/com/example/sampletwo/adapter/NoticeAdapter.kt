package com.example.sampletwo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.R
import com.example.sampletwo.databinding.NoticeItemListBinding
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.room.NoticeEntity

class NoticeAdapter : ListAdapter<NoticeEntity, NoticeAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(val binding: NoticeItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoticeItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SimpleDateFormat", "ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticeEntity = getItem(position)
        holder.binding.apply {
            notice = noticeEntity
            layoutTitleTimeBtn.setOnClickListener {
                if (textNoticeContent.visibility == View.VISIBLE) {
                    textNoticeContent.hide()
                    textNoticeTitle.apply {
                        setTextColor(root.context.getColor(R.color.black))
                        maxLines = 1
                    }
                    btnVisible.animate().apply {
                        duration = 100
                        rotation(0f)
                    }
                } else {
                    textNoticeContent.show()
                    textNoticeTitle.apply {
                        setTextColor(root.context.getColor(R.color.confirm_btn_color))
                        maxLines = 100
                    }
                    btnVisible.animate().apply {
                        duration = 100
                        rotation(180f)
                    }
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NoticeEntity>() {
        override fun areItemsTheSame(oldItem: NoticeEntity, newItem: NoticeEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoticeEntity, newItem: NoticeEntity): Boolean {
            return oldItem == newItem
        }
    }
}