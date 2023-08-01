package com.example.sampletwo.retrofit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sampletwo.retrofit.model.ApiData
import com.example.sampletwo.retrofit.service.APIService
import com.example.sampletwo.util.PagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: APIService) {
    fun fetchData(): Flow<PagingData<ApiData.DataList>> = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { PagingDataSource(apiService) }).flow
}