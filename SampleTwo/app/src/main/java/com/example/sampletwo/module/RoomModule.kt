package com.example.sampletwo.module

import android.content.Context
import com.example.sampletwo.room.NoticeDao
import com.example.sampletwo.room.NoticeDatabase
import com.example.sampletwo.room.NoticeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideNoticeDatabase(@ApplicationContext context: Context): NoticeDatabase =
        NoticeDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideDao(db: NoticeDatabase): NoticeDao = db.noticeDao()

    @Singleton
    @Provides
    fun provideRepository(db: NoticeDatabase): NoticeRepository = NoticeRepository(db)
}