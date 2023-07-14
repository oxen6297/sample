package com.example.sampletwo.fragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.R
import com.example.sampletwo.adapter.CertificateViewPagerAdapter
import com.example.sampletwo.adapter.ViewPagerAdapter
import com.example.sampletwo.databinding.FragmentCertificateBinding
import com.example.sampletwo.datastore.UserInfo
import com.example.sampletwo.extension.dpToPx
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CertificateFragment :
    BaseFragmentDataBinding<FragmentCertificateBinding, DataStoreViewModel>(R.layout.fragment_certificate) {

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

    override fun setUpBinding(view: View) {
        observeData(view.context)
        viewModel.readData()
    }

    private fun observeData(context: Context) {
        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            val value: Int = context.dpToPx(30)
            binding.viewpagerCertificateCard.apply {
                adapter =
                    if (userInfo.certificateDate == 0L) ViewPagerAdapter(requireActivity())
                    else CertificateViewPagerAdapter(userInfo, ::itemRotateClickListener).apply {
                        submitList(arrayOfNulls<UserInfo>(3).toList())
                    }

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

    private fun itemRotateClickListener(isFront: Boolean, front: View, back: View) {
        if (isFront) {
            back.apply {
                rotateCard(this, R.animator.rotate_reverse_animation)
                show()
            }
            front.apply {
                rotateCard(this, R.animator.rotate_out_animation).doOnEnd { hide() }
            }
        } else {
            front.apply {
                rotateCard(this, R.animator.rotate_animation)
                show()
            }
            back.apply {
                rotateCard(this, R.animator.rotate_reverse_out_animation).doOnEnd { hide() }
            }
        }
    }

    private fun rotateCard(view: View, animationResource: Int): Animator {
        val displayDensity = view.context.resources.displayMetrics.density
        return AnimatorInflater.loadAnimator(requireContext(), animationResource).apply {
            setTarget(view)
            view.cameraDistance = displayDensity * 8000
            start()
        }
    }
}
