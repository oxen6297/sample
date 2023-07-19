package com.example.sampletwo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoticeDao {

    @Query("SELECT * FROM NoticeEntity ORDER BY id DESC LIMIT 1")
    fun getRecentNotice(): NoticeEntity?

    @Query("SELECT * FROM NoticeEntity ORDER BY id")
    fun getNotice(): List<NoticeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNotice(noticeEntity: NoticeEntity)
}