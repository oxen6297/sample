package com.example.sampletwo.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sampletwo.extension.toList
import com.example.sampletwo.retrofit.model.ApiModel
import com.example.sampletwo.retrofit.model.NorthData
import com.example.sampletwo.retrofit.service.APIService
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class PagingDataSource(private val apiService: APIService) : PagingSource<Int, NorthData>() {

    private var response: Response<ApiModel>

    init {
        runBlocking {
            response = apiService.getData(
                SERVICE_KEY,
                PAGE_NO,
                NUM_OF_ROWS,
                KEYWORD,
                EXC_MAN,
                START_YMD,
                END_YMD
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NorthData>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NorthData> {
        return try {
            val page = params.key ?: 1
            val item = response.body()?.items?.toList<NorthData>() ?: emptyList()

            LoadResult.Page(
                data = item,
                prevKey = null,
                nextKey = if (item.size == 50) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    fun totalCnt(): String = response.body()?.totalCount.toString()

    companion object {
        private const val SERVICE_KEY =
            "+P2DTWAGXAMgeSwIUQHVELlHOljWw5Yj5U6nt7PW+EEKUP2ZN4Ewa2zC0yHVmE+vwCfJhbN9jyJx/FCnsqY7sA=="
        private const val NUM_OF_ROWS = "50"
        private const val PAGE_NO = "1"
        private const val KEYWORD = " "
        private const val EXC_MAN = "리병철"
        private const val START_YMD = "20171010"
        private const val END_YMD = "20230731"
    }
}