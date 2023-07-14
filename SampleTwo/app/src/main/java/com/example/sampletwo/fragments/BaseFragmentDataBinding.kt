package com.example.sampletwo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.navigation.NavigationCommand
import com.example.sampletwo.viewmodels.BaseViewModel

abstract class BaseFragmentDataBinding<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutId: Int) :
    Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        observeNavigation()
        setUpBinding(view)
    }

    protected abstract fun setUpBinding(view: View)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeNavigation() {
        viewModel.navigation.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { navCommand ->
                handleNavigation(navCommand)
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.Direction -> findNavController().navigate(navCommand.direction)
            is NavigationCommand.Back -> findNavController().popBackStack()
        }
    }
}