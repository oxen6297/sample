package com.example.sampletwo.datastore

data class UserInfo(
    val image: String,
    val name: String,
    val certificateNumber: String,
    val birth: String,
    val recentCompany: String,
    val certificateDate: Long = System.currentTimeMillis()
)
