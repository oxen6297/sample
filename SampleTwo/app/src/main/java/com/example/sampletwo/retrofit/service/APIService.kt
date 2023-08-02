package com.example.sampletwo.retrofit.service

import com.example.sampletwo.retrofit.model.ApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("getOthbcact")
    suspend fun getData(
        @Query("serviceKey") serviceKey: String,
        @Query("pageNo") pageNo: String,
        @Query("numOfRows") numOfRows: String,
        @Query("keyword") keyword: String,
        @Query("excman") excMan: String,
        @Query("bgng_ymd") bgngYmd: String,
        @Query("end_ymd") endYmd: String,
    ): Response<ApiModel>
}