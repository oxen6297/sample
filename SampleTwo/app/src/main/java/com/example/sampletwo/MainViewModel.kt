package com.example.sampletwo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val agreeOne = MutableLiveData(false)
    val agreeTwo = MutableLiveData(false)
    val agreeThree = MutableLiveData(false)
    val agreeFour = MutableLiveData(false)
    val agreeAll = MutableLiveData(false)

    fun setAgreeAll(isCheck: Boolean) {
        agreeOne.value = isCheck
        agreeTwo.value = isCheck
        agreeThree.value = isCheck
        agreeFour.value = isCheck
    }

    fun isAgreeAll() {
        agreeAll.value = isAgree()
    }
    private fun isAgree(): Boolean {
        return agreeOne.value ?: false && agreeTwo.value ?: false && agreeThree.value ?: false
    }
}