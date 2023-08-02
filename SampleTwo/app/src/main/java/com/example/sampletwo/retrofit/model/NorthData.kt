package com.example.sampletwo.retrofit.model

import com.google.gson.annotations.SerializedName

data class NorthData(
    @SerializedName("nes_cn")
    val nesCn: String,
    @SerializedName("nes_ymd")
    val nesYmd: String,
    @SerializedName("excman")
    val excMan: String,
)
