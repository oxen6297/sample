package com.example.sampletwo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sampletwo.fragments.viewpager.CertificateCardOneFragment
import com.example.sampletwo.fragments.viewpager.CertificateCardTwoFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CertificateCardOneFragment()
            else -> CertificateCardTwoFragment()
        }
    }
}