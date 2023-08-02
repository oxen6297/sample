package com.example.sampletwo.retrofit.model

import com.google.gson.JsonElement


data class ApiModel(
    val resultCode: String,
    val resultMsg: String,
    val totalCount: Int,
    val numOfRows: Int,
    val pageNo: Int,
    val items: JsonElement
)
