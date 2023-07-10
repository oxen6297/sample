package com.example.sampletwo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampletwo.datastore.DataStoreRepository
import com.example.sampletwo.datastore.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {

    val signUpName = MutableLiveData<String>()
    val signUpCertificationNumber = MutableLiveData<String>()
    val signUpBirth = MutableLiveData<String>()
    val signUpRecentCompany = MutableLiveData<String>()
    val signUpImage = MutableLiveData<String>()

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: MutableLiveData<UserInfo>
        get() = _userInfo

    fun saveData() {
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
}