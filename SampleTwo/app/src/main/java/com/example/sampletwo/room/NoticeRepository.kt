package com.example.sampletwo.room

import javax.inject.Inject


class NoticeRepository @Inject constructor(private val db: NoticeDatabase) {

    fun getRecentNotice(): NoticeEntity? = db.noticeDao().getRecentNotice()

    fun getNotice(): List<NoticeEntity> = db.noticeDao().getNotice()

    fun addNotice(noticeEntity: NoticeEntity) = db.noticeDao().addNotice(noticeEntity)
}