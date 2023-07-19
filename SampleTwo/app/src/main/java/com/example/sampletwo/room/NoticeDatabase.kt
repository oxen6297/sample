package com.example.sampletwo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [NoticeEntity::class], version = 2)
abstract class NoticeDatabase : RoomDatabase() {
    abstract fun noticeDao(): NoticeDao

    companion object {
        private var instance: NoticeDatabase? = null

        fun getInstance(context: Context): NoticeDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    NoticeDatabase::class.java,
                    "noticeDB"
                ).addMigrations(MIGRATION_1_TO_2).fallbackToDestructiveMigration().build()
            }
        }

        private val MIGRATION_1_TO_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.run {
                    database.execSQL("ALTER TABLE NoticeEntity ***DROP*** COLUMN time")
                    execSQL("ALTER TABLE NoticeEntity ADD time VARCHAR")
                }
            }
        }
    }
}