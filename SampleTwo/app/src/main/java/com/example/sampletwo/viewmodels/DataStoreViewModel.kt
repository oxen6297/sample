package com.example.sampletwo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sampletwo.datastore.DataStoreRepository
import com.example.sampletwo.datastore.model.UserInfo
import com.example.sampletwo.fragments.CertificateInfoThreeFragmentDirections
import com.example.sampletwo.fragments.SignUpFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    BaseViewModel() {

    val signUpName = MutableLiveData<String>()
    val signUpCertificationNumber = MutableLiveData<String>()
    val signUpBirth = MutableLiveData<String>()
    val signUpRecentCompany = MutableLiveData<String>()
    val signUpImage = MutableLiveData<String>()

    private val isBlank = MutableLiveData(false)

    val bitmap = MutableLiveData<String>()

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> get() = _userInfo

    val switchValue = MutableLiveData(false)

    val cardOne = MutableLiveData(false)
    val cardTwo = MutableLiveData(false)
    val cardThree = MutableLiveData(false)

    private val password: MutableList<Int?> = mutableListOf()
    private val _passwordList = MutableLiveData(MutableList<Int?>(6) { null })
    val passwordList: LiveData<MutableList<Int?>> get() = _passwordList

    fun clickNumberBtn(numText: Int) {
        password.add(numText)
        _passwordList.value = password
    }

    fun clickDelete() {
        password.removeAt(password.lastIndex)
        _passwordList.value = password
    }


    private fun setIsBlank() {
        isBlank.value = signUpName.value?.isNotBlank() ?: false &&
                signUpCertificationNumber.value?.isNotBlank() ?: false &&
                signUpBirth.value?.isNotBlank() ?: false &&
                signUpBirth.value?.length == 8 &&
                signUpRecentCompany.value?.isNotBlank() ?: false
    }

    private fun saveData() {
        viewModelScope.launch {
            dataStoreRepository.saveUserInfo(
                UserInfo(
                    signUpImage.value.toString(),
                    signUpName.value.toString(),
                    signUpCertificationNumber.value.toString(),
                    signUpBirth.value.toString(),
                    signUpRecentCompany.value.toString()
                )
            )
        }
    }

    fun readData() {
        viewModelScope.launch {
            dataStoreRepository.readUserInfo().collect {
                _userInfo.postValue(it)
            }
        }
    }

    fun goSignUpFragment() {
        navigate(
            CertificateInfoThreeFragmentDirections.actionCertificateInfoThreeFragmentToSignUpFragment(
                bitmap.value.toString()
            )
        )
    }

    fun goCertificateFragment() {
        setIsBlank()
        clickable()
    }

    private fun clickable() {
        if (isBlank.value == true) {
            saveData()
            navigate(SignUpFragmentDirections.actionSignUpFragmentToCertificateFragment())
        }
    }
}