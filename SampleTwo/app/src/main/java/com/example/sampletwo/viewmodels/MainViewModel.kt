package com.example.sampletwo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampletwo.fragments.CertificateFragmentDirections
import com.example.sampletwo.fragments.CertificateInfoFragmentDirections
import com.example.sampletwo.fragments.CertificateInfoTwoFragmentDirections
import com.example.sampletwo.fragments.CompleteVerifyFragmentDirections
import com.example.sampletwo.fragments.QuestionCertificationFragmentDirections

class MainViewModel : BaseViewModel() {
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

    fun goCertificateInfoTwoFragment() {
        navigate(CertificateInfoFragmentDirections.actionCertificateInfoFragmentToCertificationInfoTwoFragment())
    }

    fun goQuestionFragment() {
        navigate(
            CertificateInfoTwoFragmentDirections.actionCertificationInfoTwoFragmentToQuestionCertificationFragment(
                radioButtonClick.value.toString()
            )
        )
    }

    fun goCertificateInfoThreeFragment() {
        navigate(CompleteVerifyFragmentDirections.actionCompleteVerifyFragmentToCertificateInfoThreeFragment())
    }

    fun goCompleteVerifyFragment() {
        navigate(QuestionCertificationFragmentDirections.actionQuestionCertificationFragmentToCompleteVerifyFragment())
    }

    fun cancel() {
        navigate(QuestionCertificationFragmentDirections.actionQuestionCertificationFragmentToCertificateFragment())
    }

    fun goCertificateInfoFragment(){
        navigate(CertificateFragmentDirections.actionCertificateFragmentToCertificateInfoFragment())
    }

    enum class RadioType {
        MOBILE, NICE
    }
}