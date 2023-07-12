package com.example.sampletwo.fragments

import android.animation.AnimatorInflater
import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.R
import com.example.sampletwo.adapter.CertificateViewPagerAdapter
import com.example.sampletwo.adapter.ViewPagerAdapter
import com.example.sampletwo.databinding.FragmentCertificateBinding
import com.example.sampletwo.extension.convertDpToPixel
import com.example.sampletwo.extension.visibilityGone
import com.example.sampletwo.extension.visibilityVisible
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertificateFragment :
    BaseFragmentDataBinding<DataStoreViewModel, FragmentCertificateBinding>(R.layout.fragment_certificate) {

    override val viewModel: DataStoreViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backPressedCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallBack)
    }

    override fun setUpBinding(context: Context) {
        observeData(context)
        viewModel.readData()
    }

    private fun observeData(context: Context) =
        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            val value: Int = context.convertDpToPixel(30)
            val viewpagerAdapter =
                if (userInfo.certificateDate == 0L) ViewPagerAdapter(requireActivity())
                else CertificateViewPagerAdapter(userInfo, context, ::itemRotateClickListener)

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

    private fun itemRotateClickListener(isFront: Boolean, front: View, back: View) {
        if (isFront) {
            back.apply {
                visibilityVisible()
                setAnimation(this, R.animator.rotate_reverse_animation)
            }
            front.visibilityGone()
        } else {
            front.apply {
                setAnimation(this, R.animator.rotate_animation)
                visibilityVisible()
            }
            back.visibilityGone()
        }
    }

    private fun setAnimation(view: View, animationResource: Int) {
        AnimatorInflater.loadAnimator(requireContext(), animationResource).apply {
            setTarget(view)
            start()
        }
    }
}
