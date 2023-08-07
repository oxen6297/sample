package com.example.sampletwo.retrofit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sampletwo.retrofit.model.NorthData
import com.example.sampletwo.retrofit.service.APIService
import com.example.sampletwo.util.PagingDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class Repository @Inject constructor(apiService: APIService) {

    private val pagingDataSource = PagingDataSource(apiService)

    fun fetchData(): Flow<PagingData<NorthData>> = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { pagingDataSource }).flow

    fun getTotalCnt(): MutableStateFlow<Int?> = pagingDataSource.totalCnt
}