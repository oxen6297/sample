package com.example.sampletwo.datastore

import com.example.sampletwo.datastore.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoService {
    suspend fun saveUserInfo(userInfo: UserInfo)
    suspend fun readUserInfo(): Flow<UserInfo>
}