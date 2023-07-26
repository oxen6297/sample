package com.example.sampletwo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PasswordViewModel : BaseViewModel() {

    private val password = MutableList<Int?>(6) { null }
    private val _passwordList = MutableLiveData(MutableList<Int?>(6) { null })
    val passwordList: LiveData<MutableList<Int?>> get() = _passwordList

    fun clickNumberBtn(numText: Int) {
        val nullIndex = password.indexOfFirst { it == null }
        if (nullIndex != -1) {
            password[nullIndex] = numText
            _passwordList.value = password
        }
    }

    fun clickDelete() {
        val notNullIndex = password.indexOfLast { it != null }
        if (notNullIndex != -1) {
            password[notNullIndex] = null
            _passwordList.value = password
        }
    }

    fun initPassword() {
        password.fill(null)
        _passwordList.value = password
    }
}