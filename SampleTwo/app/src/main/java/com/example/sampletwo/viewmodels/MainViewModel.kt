package com.example.sampletwo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val agreeOne = MutableLiveData(false)
    val agreeTwo = MutableLiveData(false)
    val agreeThree = MutableLiveData(false)
    val agreeFour = MutableLiveData(false)
    val agreeAll = MutableLiveData(false)

    private val _radioButtonClick = MutableLiveData(RadioType.MOBILE)
    val radioButtonClick: LiveData<RadioType>
        get() = _radioButtonClick

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

    fun onClickRadioButton(type: RadioType) {
        when (type) {
            RadioType.NICE -> _radioButtonClick.value = RadioType.NICE
            RadioType.MOBILE -> _radioButtonClick.value = RadioType.MOBILE
        }
    }

    enum class RadioType {
        MOBILE, NICE
    }
}