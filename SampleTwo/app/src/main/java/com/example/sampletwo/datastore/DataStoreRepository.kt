package com.example.sampletwo.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sampletwo.datastore.model.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(private val context: Context) : UserInfoService {
    private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

    override suspend fun saveUserInfo(userInfo: UserInfo) {
        context.dataStore.edit { userInfoSet ->
            userInfoSet[image] = userInfo.image
            userInfoSet[name] = userInfo.name
            userInfoSet[certificateNumber] = userInfo.certificateNumber
            userInfoSet[birth] = userInfo.birth
            userInfoSet[certificateDate] = userInfo.certificateDate
            userInfoSet[recentCompany] = userInfo.recentCompany
        }
    }

    override suspend fun readUserInfo(): Flow<UserInfo?> = context.dataStore.data.map { userInfo ->
        if (userInfo.asMap().values.isEmpty()) null
        else UserInfo(
            image = userInfo[image] ?: "",
            name = userInfo[name] ?: "",
            certificateNumber = userInfo[certificateNumber] ?: "",
            birth = userInfo[birth] ?: "",
            certificateDate = userInfo[certificateDate] ?: 0L,
            recentCompany = userInfo[recentCompany] ?: ""
        )
    }

    companion object {
        private const val DATA_STORE_NAME = "user_info"
        private val image = stringPreferencesKey(IMAGE)
        private val name = stringPreferencesKey(NAME)
        private val certificateNumber = stringPreferencesKey(CERTIFICATE_NUMBER)
        private val birth = stringPreferencesKey(BIRTH)
        private val certificateDate = longPreferencesKey(CERTIFICATE_DATE)
        private val recentCompany = stringPreferencesKey(RECENT_COMPANY)
    }
}