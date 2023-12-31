package com.example.sampletwo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampletwo.extension.indexOfFirstOrNull
import com.example.sampletwo.extension.indexOfLastOrNull

class PasswordViewModel : BaseViewModel() {

    private val password = MutableList<Int?>(6) { null }

    private val _passwordList = MutableLiveData(List<Int?>(6) { null })
    val passwordList: LiveData<List<Int?>> get() = _passwordList

    private val _randomNumber = MutableLiveData(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled())
    val randomNumber: LiveData<List<Int>> get() = _randomNumber

    fun clickNumberBtn(position: Int) {
        val nullIndex = password.indexOfFirstOrNull { it == null }
        if (nullIndex != null) {
            password[nullIndex] = randomNumber.value?.get(position)
            _passwordList.value = password
        }
    }

    fun clickDelete() {
        val notNullIndex = password.indexOfLastOrNull { it != null }
        if (notNullIndex != null) {
            password[notNullIndex] = null
            _passwordList.value = password
        }
    }

    fun initPassword() {
        password.fill(null)
        _passwordList.value = password
    }

    fun mixKeyPad() {
        _randomNumber.value = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled()
    }
}