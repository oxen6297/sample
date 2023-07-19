package com.example.sampletwo.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
        Room.databaseBuilder(context, NoticeDatabase::class.java, "noticeDB").addMigrations(
            MIGRATION_1_TO_2
        ).build()

    @Singleton
    @Provides
    fun provideDao(db: NoticeDatabase): NoticeDao = db.noticeDao()

    @Singleton
    @Provides
    fun provideRepository(db: NoticeDatabase): NoticeRepository = NoticeRepository(db)

    private val MIGRATION_1_TO_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.run {
                database.execSQL("ALTER TABLE NoticeEntity ***DROP*** COLUMN time")
                execSQL("ALTER TABLE NoticeEntity ADD time VARCHAR")
            }
        }
    }
}