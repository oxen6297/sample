package com.example.sampletwo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.R
import com.example.sampletwo.databinding.CertificateViewpagerItemListBinding
import com.example.sampletwo.datastore.model.UserInfo
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.isShow
import com.example.sampletwo.extension.show
import com.example.sampletwo.extension.singleClickListener
import com.example.sampletwo.util.BitmapConverter
import java.text.SimpleDateFormat

class CertificateViewPagerAdapter(
    private val userInfo: UserInfo,
    private val clickListener: (Boolean, View, View, Int) -> Unit,
    private val cardOne: Boolean,
    private val cardTwo: Boolean,
    private val cardThree: Boolean
) : ListAdapter<UserInfo, CertificateViewPagerAdapter.ViewPagerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = CertificateViewpagerItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewPagerViewHolder(binding)
    }

    class ViewPagerViewHolder(val binding: CertificateViewpagerItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun setVisible(isBack: Boolean, front: View, back: View) {
        if (isBack) {
            front.hide()
            back.show()
        } else {
            back.hide()
            front.show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.binding.apply {
            user = userInfo
            executePendingBindings()
            imgPhoto.setImageBitmap(BitmapConverter().stringToBitmap(userInfo.image))
            textBirthInfo.text = StringBuilder(userInfo.birth).insert(4, ".").insert(7, ".")
            textStartCertificateInfo.text =
                SimpleDateFormat("yyyy.MM").format(userInfo.certificateDate)

            layoutRotate.singleClickListener {
                clickListener(
                    layoutCardFront.isShow(),
                    layoutCardFront,
                    layoutCardBack,
                    position
                )
            }

            when (position) {
                0 -> {
                    setVisible(cardOne, layoutCardFront, layoutCardBack)
                    textReceiver.text = root.context.getString(R.string.receiver)
                    textCertificate.text = root.context.getString(R.string.certificate_card_one)
                    layoutCardFront.setBackgroundResource(R.drawable.card_02_front)
                    imgCardTop.setBackgroundResource(R.drawable.cart_top_yellow_background)
                    textReceiverBack.text = root.context.getString(R.string.receiver)
                }

                1 -> {
                    setVisible(cardTwo, layoutCardFront, layoutCardBack)
                    textReceiver.text = root.context.getString(R.string.employee)
                    textCertificate.text = root.context.getString(R.string.certificate_card_one)
                    layoutCardFront.setBackgroundResource(R.drawable.card_01_front)
                    imgCardTop.setBackgroundResource(R.drawable.cart_top_blue_background)
                    textReceiverBack.text = root.context.getString(R.string.employee)
                    textCertificateNumber.text = root.context.getString(R.string.employee_number)
                    layoutStartCertificate.hide()
                    layoutRecentCompany.hide()
                }

                2 -> {
                    setVisible(cardThree, layoutCardFront, layoutCardBack)
                    textCertificate.text = root.context.getString(R.string.verify_certification)
                    layoutCardFront.setBackgroundResource(R.drawable.card_04_front)
                    imgCardTop.setBackgroundResource(R.drawable.card_top_black_background)
                    textCertificateBack.text = root.context.getString(R.string.verify)
                    textReceiver.hide()
                    textReceiverBack.hide()
                    layoutStartCertificate.hide()
                    layoutRecentCompany.hide()
                    layoutCertificateNumber.hide()
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem.certificateDate == newItem.certificateDate
        }

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean {
            return oldItem == newItem
        }
    }
}