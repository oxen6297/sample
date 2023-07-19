package com.example.sampletwo.room


class NoticeRepository (private val db: NoticeDatabase) {

    fun getRecentNotice(): NoticeEntity? = db.noticeDao().getRecentNotice()

    fun getNotice(): List<NoticeEntity> = db.noticeDao().getNotice()

    fun addNotice(noticeEntity: NoticeEntity) = db.noticeDao().addNotice(noticeEntity)
}