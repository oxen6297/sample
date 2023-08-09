package com.example.sampletwo.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sampletwo.retrofit.ApiResult
import com.example.sampletwo.retrofit.Repository
import com.example.sampletwo.retrofit.model.NorthData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    private val _data = MutableStateFlow<ApiResult<PagingData<NorthData>>>(ApiResult.Loading)
    val data = _data.asStateFlow()

    private val _totalCnt = MutableStateFlow<Int?>(null)
    val totalCnt = _totalCnt.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            delay(1000)
            try {
                repository.fetchData().cachedIn(this).collect {
                    _data.emit(ApiResult.Success(it))
                }
            } catch (e: Exception) {
                _data.emit(ApiResult.Error(e))
            }
        }
    }


    fun getTotalCnt() {
        viewModelScope.launch {
            try {
                repository.getTotalCnt().collect {
                    _totalCnt.emit(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}