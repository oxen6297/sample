package com.example.sampletwo.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(private val context: Context) : UserInfoService {
    private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

    override suspend fun saveUserInfo(userInfo: UserInfo) {
        context.dataStore.edit { userInfoSet ->
            userInfoSet[IMAGE] = userInfo.image
            userInfoSet[NAME] = userInfo.name
            userInfoSet[CERTIFICATE_NUMBER] = userInfo.certificateNumber
            userInfoSet[BIRTH] = userInfo.birth
            userInfoSet[CERTIFICATE_DATE] = userInfo.certificateDate
            userInfoSet[RECENT_COMPANY] = userInfo.recentCompany
        }
    }

    override suspend fun readUserInfo(): Flow<UserInfo> = context.dataStore.data.map { userInfo ->
        UserInfo(
            image = userInfo[IMAGE] ?: "",
            name = userInfo[NAME] ?: "",
            certificateNumber = userInfo[CERTIFICATE_NUMBER] ?: "",
            birth = userInfo[BIRTH] ?: "",
            certificateDate = userInfo[CERTIFICATE_DATE] ?: 0L,
            recentCompany = userInfo[RECENT_COMPANY] ?: ""
        )
    }

    companion object {
        private const val DATA_STORE_NAME = "user_info"
        private val IMAGE = stringPreferencesKey("image")
        private val NAME = stringPreferencesKey("name")
        private val CERTIFICATE_NUMBER = stringPreferencesKey("certificateNumber")
        private val BIRTH = stringPreferencesKey("birth")
        private val CERTIFICATE_DATE = longPreferencesKey("certificateDate")
        private val RECENT_COMPANY = stringPreferencesKey("recentCompany")
    }
}