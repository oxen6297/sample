package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import com.example.sampletwo.R

abstract class BaseFragment<B : ViewBinding>(private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> B) :
    Fragment() {

    private var _binding: B? = null
    val binding get() = _binding!!

    val nextNavOptions = NavOptions.Builder().setEnterAnim(R.anim.enter_from_right)
        .setExitAnim(R.anim.exit_to_left).build()
    val backNavOptions = NavOptions.Builder().setEnterAnim(R.anim.enter_from_left)
        .setExitAnim(R.anim.exit_to_right).build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingFactory.invoke(inflater, container, false)
        return binding.root
    }
}