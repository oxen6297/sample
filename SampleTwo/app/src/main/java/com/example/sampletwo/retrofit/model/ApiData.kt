package com.example.sampletwo.retrofit.model

import com.google.gson.annotations.SerializedName

data class ApiData(
    val resultCode: String,
    val resultMsg: String,
    val totalCount: Int,
    val numOfRows: Int,
    val pageNo: Int,
    val items: List<DataList>
){
    data class DataList(
        @SerializedName("nes_cn")
        val nesCn: String,
        @SerializedName("nes_ymd")
        val nesYmd: String,
        @SerializedName("excman")
        val excMan: String,
    )
}
