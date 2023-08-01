package com.example.sampletwo.retrofit

import com.example.sampletwo.retrofit.service.APIService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val okHttpClient = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://apis.data.go.kr/1250000/othbcact/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideRepository(apiService: APIService) = Repository(apiService)
}