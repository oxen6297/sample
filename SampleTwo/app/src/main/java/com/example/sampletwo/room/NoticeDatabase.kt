package com.example.sampletwo.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoticeEntity::class], version = 2)
abstract class NoticeDatabase : RoomDatabase() {
    abstract fun noticeDao(): NoticeDao
}