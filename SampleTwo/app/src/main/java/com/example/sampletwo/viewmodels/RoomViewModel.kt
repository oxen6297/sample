package com.example.sampletwo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sampletwo.room.NoticeEntity
import com.example.sampletwo.room.NoticeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val repository: NoticeRepository) :
    BaseViewModel() {

    private val _noticeData = MutableLiveData<List<NoticeEntity>>()
    val noticeData: LiveData<List<NoticeEntity>> get() = _noticeData

    private val _recentNotice = MutableLiveData<NoticeEntity>()
    val recentNotice: LiveData<NoticeEntity> get() = _recentNotice

    fun getNotice() {
        viewModelScope.launch {
            _noticeData.postValue(repository.getNotice())
        }
    }

    fun getRecentNotice() {
        viewModelScope.launch {
            _recentNotice.postValue(repository.getRecentNotice())
        }
    }
}