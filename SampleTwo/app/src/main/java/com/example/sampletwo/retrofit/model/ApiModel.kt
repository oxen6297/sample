package com.example.sampletwo.retrofit.model


data class ApiModel<T>(
    val resultCode: String,
    val resultMsg: String,
    val totalCount: Int,
    val numOfRows: Int,
    val pageNo: Int,
    val items: List<T>
)
