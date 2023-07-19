package com.example.sampletwo.fragments

import android.animation.Animator
import android.animation.AnimatorInflater
import android.annotation.SuppressLint
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
import com.example.sampletwo.datastore.ONE_IS_FRONT
import com.example.sampletwo.datastore.TWO_IS_FRONT
import com.example.sampletwo.datastore.Three_IS_FRONT
import com.example.sampletwo.datastore.model.CardState
import com.example.sampletwo.datastore.model.TooltipCancel
import com.example.sampletwo.datastore.model.UserInfo
import com.example.sampletwo.extension.dpToPx
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.room.NoticeEntity
import com.example.sampletwo.viewmodels.DataStoreViewModel
import com.example.sampletwo.viewmodels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CertificateFragment :
    BaseFragment<FragmentCertificateBinding, DataStoreViewModel>(R.layout.fragment_certificate) {

    override val viewModel: DataStoreViewModel by viewModels()
    private val roomViewModel: RoomViewModel by viewModels()

    companion object {
        private val Context.dataStoreTooltip by preferencesDataStore("cancelTooltip")
        private val Context.dataStoreCardState by preferencesDataStore("cardState")
        private val isCancel = booleanPreferencesKey(IS_CANCEL)
        private val pageOne = booleanPreferencesKey(ONE_IS_FRONT)
        private val pageTwo = booleanPreferencesKey(TWO_IS_FRONT)
        private val pageThree = booleanPreferencesKey(Three_IS_FRONT)
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
        observeNotice()
        roomViewModel.getRecentNotice()
        readCardState(view.context)
        observeData(view.context)
        viewModel.readData()
        binding.apply {
            imgCancel.setOnClickListener {
                saveTooltip(view.context)
            }
        }
    }

    private fun observeNotice() = roomViewModel.recentNotice.observe(this, ::noticeWatcher)

    @SuppressLint("SimpleDateFormat")
    private fun noticeWatcher(noticeEntity: NoticeEntity?) {
        binding.apply {
            noticeEntity?.let {
                textNoticeTitle.text = it.title
                textNoticeContent.text = it.content
                textNoticeTime.text = it.time
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
                    } else CertificateViewPagerAdapter(
                        userInfo,
                        ::itemRotateClickListener,
                        viewModel.cardOne.value ?: false,
                        viewModel.cardTwo.value ?: false,
                        viewModel.cardThree.value ?: false
                    ).apply {
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

    private fun itemRotateClickListener(isFront: Boolean, front: View, back: View, position: Int) {
        if (isFront) {
            back.apply {
                rotateCard(this, R.animator.rotate_reverse_animation)
                show()
                setCardState(position, true)
            }
            front.apply {
                rotateCard(this, R.animator.rotate_out_animation).doOnEnd { hide() }
            }
        } else {
            front.apply {
                rotateCard(this, R.animator.rotate_animation)
                show()
                setCardState(position, false)
            }
            back.apply {
                rotateCard(this, R.animator.rotate_reverse_out_animation).doOnEnd { hide() }
            }
        }
    }

    private fun setCardState(position: Int, isFront: Boolean) {
        viewModel.apply {
            when (position) {
                0 -> cardOne.value = isFront
                1 -> cardTwo.value = isFront
                2 -> cardThree.value = isFront
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

    private fun saveCardState(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStoreCardState.edit { preference ->
                viewModel.apply {
                    preference[pageOne] = cardOne.value ?: false
                    preference[pageTwo] = cardTwo.value ?: false
                    preference[pageThree] = cardThree.value ?: false
                }
            }
        }
    }

    private fun readCardState(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            context.dataStoreCardState.data.map { preference ->
                CardState(
                    preference[pageOne] ?: false,
                    preference[pageTwo] ?: false,
                    preference[pageThree] ?: false
                )
            }.collect { cardState ->
                viewModel.apply {
                    cardOne.value = cardState.pageOne
                    cardTwo.value = cardState.pageTwo
                    cardThree.value = cardState.pageThree
                }
            }
        }
    }

    private fun readTooltip(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            context.dataStoreTooltip.data.map { preference ->
                TooltipCancel(preference[isCancel] ?: false)
            }.collect { toolTipCancel ->
                if (toolTipCancel.isCanceled) binding.layoutTooltip.hide()
                else binding.layoutTooltip.show()
            }
        }
    }

    private fun saveTooltip(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            context.dataStoreTooltip.edit { preference ->
                preference[isCancel] = true
            }
        }
    }

    override fun onStop() {
        super.onStop()
        saveCardState(requireContext())
    }
}
