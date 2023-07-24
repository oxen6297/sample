package com.example.sampletwo.room

import javax.inject.Inject


class NoticeRepository @Inject constructor(private val db: NoticeDatabase) {

    suspend fun getRecentNotice(): NoticeEntity? = db.noticeDao().getRecentNotice()

    suspend fun getNotice(): List<NoticeEntity> = db.noticeDao().getNotice()

    fun addNotice(noticeEntity: NoticeEntity) = db.noticeDao().addNotice(noticeEntity)
}