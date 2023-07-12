package com.example.sampletwo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.R
import com.example.sampletwo.databinding.CertificateViewpagerItemListBinding
import com.example.sampletwo.datastore.UserInfo
import com.example.sampletwo.extension.visibilityGone
import com.example.sampletwo.util.BitmapConverter
import java.text.SimpleDateFormat

class CertificateViewPagerAdapter(
    private val userInfo: UserInfo,
    private val context: Context,
    private val clickListener: (Boolean, View, View) -> Unit
) :
    RecyclerView.Adapter<CertificateViewPagerAdapter.ViewPagerViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    inner class ViewPagerViewHolder(val binding: CertificateViewpagerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserInfo) {
            binding.apply {
                user = item
                imgPhoto.setImageBitmap(BitmapConverter().stringToBitmap(item.image))
                textBirthInfo.text = StringBuilder(item.birth).insert(4, ".").insert(7, ".")
                textStartCertificateInfo.text =
                    SimpleDateFormat("yyyy.MM").format(item.certificateDate)

                layoutRotate.setOnClickListener {
                    if (adapterPosition != 100){
                        clickListener(
                            layoutCardFront.visibility == View.VISIBLE,
                            layoutCardFront,
                            layoutCardBack
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = CertificateViewpagerItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(userInfo)
        holder.binding.apply {
            when (position) {
                0 -> {
                    textReceiver.text = context.getString(R.string.receiver)
                    textCertificate.text = context.getString(R.string.certificate_card_one)
                    layoutCardFront.setBackgroundResource(R.drawable.card_02_front)
                    imgCardTop.setBackgroundResource(R.drawable.cart_top_yellow_background)
                    textReceiverBack.text = context.getString(R.string.receiver)
                }

                1 -> {
                    textReceiver.text = context.getString(R.string.employee)
                    textCertificate.text = context.getString(R.string.certificate_card_one)
                    layoutCardFront.setBackgroundResource(R.drawable.card_01_front)
                    imgCardTop.setBackgroundResource(R.drawable.cart_top_blue_background)
                    textReceiverBack.text = context.getString(R.string.employee)
                    textCertificateNumber.text = context.getString(R.string.employee_number)
                    layoutStartCertificate.visibilityGone()
                    layoutRecentCompany.visibilityGone()
                }

                2 -> {
                    textCertificate.text = context.getString(R.string.verify_certification)
                    layoutCardFront.setBackgroundResource(R.drawable.card_04_front)
                    imgCardTop.setBackgroundResource(R.drawable.card_top_black_background)
                    textCertificateBack.text = context.getString(R.string.verify)
                    textReceiver.visibilityGone()
                    textReceiverBack.visibilityGone()
                    layoutStartCertificate.visibilityGone()
                    layoutRecentCompany.visibilityGone()
                    layoutCertificateNumber.visibilityGone()
                }
            }
        }
    }

    override fun getItemCount(): Int = 3
}