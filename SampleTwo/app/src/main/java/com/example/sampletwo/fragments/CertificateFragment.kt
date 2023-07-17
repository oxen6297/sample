package com.example.sampletwo.fragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.animation.doOnEnd
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletwo.R
import com.example.sampletwo.adapter.CertificateViewPagerAdapter
import com.example.sampletwo.adapter.ViewPagerAdapter
import com.example.sampletwo.databinding.FragmentCertificateBinding
import com.example.sampletwo.datastore.IS_CANCEL
import com.example.sampletwo.datastore.model.TooltipCancel
import com.example.sampletwo.datastore.model.UserInfo
import com.example.sampletwo.extension.dpToPx
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CertificateFragment :
    BaseFragment<FragmentCertificateBinding, DataStoreViewModel>(R.layout.fragment_certificate) {

    override val viewModel: DataStoreViewModel by viewModels()

    companion object {
        private val Context.dataStore by preferencesDataStore("cancelTooltip")
        private val isCancel = booleanPreferencesKey(IS_CANCEL)
    }

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
        binding.apply {
            imgCancel.setOnClickListener {
                saveTooltip(view.context)
            }
        }
    }

    private fun observeData(context: Context) {
        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            val value: Int = context.dpToPx(30)
            binding.viewpagerCertificateCard.apply {
                adapter =
                    if (userInfo.certificateDate == 0L) {
                        binding.layoutTooltip.hide()
                        ViewPagerAdapter(requireActivity())
                    } else CertificateViewPagerAdapter(userInfo, ::itemRotateClickListener).apply {
                        readTooltip(context)
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

    private fun saveTooltip(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            context.dataStore.edit { cancelTooltip ->
                cancelTooltip[isCancel] = true
            }
        }
    }

    private fun readTooltip(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            context.dataStore.data.map { cancelTooltip ->
                TooltipCancel(cancelTooltip[isCancel] ?: false)
            }.collect {
                if (it.isCanceled) binding.layoutTooltip.hide()
                else binding.layoutTooltip.show()
            }
        }
    }
}
