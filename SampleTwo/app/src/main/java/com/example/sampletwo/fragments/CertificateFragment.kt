package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.adapter.ViewPagerAdapter
import com.example.sampletwo.databinding.FragmentCertificateBinding
import com.example.sampletwo.extension.convertDpToPixel

class CertificateFragment :
    BaseFragment<FragmentCertificateBinding>(FragmentCertificateBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewpagerAdapter = ViewPagerAdapter(requireActivity())
        val value: Int = view.context.convertDpToPixel(30)
        binding.viewpagerCertificateCard.apply {
            adapter = viewpagerAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer { page, position ->
                if (position <= 0) page.translationY = -position * value
                else if (position > 0) page.translationY = position * value
            }
        }
    }
}
