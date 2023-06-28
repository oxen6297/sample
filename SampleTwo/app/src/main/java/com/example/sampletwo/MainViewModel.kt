package com.example.sampletwo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val agreeOne = MutableLiveData(false)
    val agreeTwo = MutableLiveData(false)
    val agreeThree = MutableLiveData(false)
    val isAgree = MutableLiveData(false)

    fun setValueIsAgree() {
        isAgree.value =
            agreeOne.value ?: false && agreeTwo.value ?: false && agreeThree.value ?: false
    }
}