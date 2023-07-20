package com.example.sampletwo.fragments

import android.content.Context
import android.view.View
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sampletwo.R
import com.example.sampletwo.databinding.FragmentPasswordBinding
import com.example.sampletwo.datastore.PASSWORD
import com.example.sampletwo.extension.hide
import com.example.sampletwo.extension.show
import com.example.sampletwo.viewmodels.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PasswordFragment :
    BaseFragment<FragmentPasswordBinding, DataStoreViewModel>(R.layout.fragment_password) {

    override val viewModel: DataStoreViewModel by viewModels()

    companion object {
        private val Context.dataStorePassword by preferencesDataStore("password")
        private val passwordKey = stringPreferencesKey(PASSWORD)
    }

    override fun setUpBinding(view: View) {
        binding.vm = viewModel
        observePasswordData()
    }

    private fun observePasswordData() =
        viewModel.passwordList.observe(viewLifecycleOwner, ::passwordWatcher)


    private fun passwordWatcher(password: MutableList<Int?>) {
        binding.apply {
            val passwordImageViews = listOf(
                imgPasswordOne,
                imgPasswordTwo,
                imgPasswordThree,
                imgPasswordFour,
                imgPasswordFive,
                imgPasswordSix
            )

            password.forEachIndexed { index, value ->
                val drawableRes = if (value == null) R.drawable.password_circle_background
                else R.drawable.password_write_circle_background

                passwordImageViews[index].setImageResource(drawableRes)
            }

            viewLifecycleOwner.lifecycleScope.launch {
                if (password[5] != null && textPassword.visibility == View.VISIBLE) {
                    savePassword(password.joinToString())
                    textPassword.hide()
                    textConfirmPassword.show()
                    viewModel.initPassword()
                } else if (password[5] != null && textConfirmPassword.visibility == View.VISIBLE && readPassword() != password.joinToString()) {
                    textNotCorrectPassword.show()
                } else if (password[5] != null && textConfirmPassword.visibility == View.VISIBLE && readPassword() == password.joinToString()) {
                    textNotCorrectPassword.hide()
                    findNavController().navigate(PasswordFragmentDirections.actionPasswordFragmentToBioVerifyFragment())
                }
            }
        }
    }

    private fun savePassword(password: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            requireContext().dataStorePassword.edit { preference ->
                preference[passwordKey] = password
            }
        }
    }

    private suspend fun readPassword(): String {
        return requireContext().dataStorePassword.data
            .map { preferences ->
                preferences[passwordKey] ?: ""
            }.first()
    }
}