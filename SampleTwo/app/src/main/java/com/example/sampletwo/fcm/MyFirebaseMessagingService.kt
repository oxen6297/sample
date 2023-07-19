package com.example.sampletwo.fcm

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.sampletwo.R
import com.example.sampletwo.activities.MainActivity
import com.example.sampletwo.room.NoticeEntity
import com.example.sampletwo.room.NoticeRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var repository: NoticeRepository

    override fun onNewToken(token: String) {
        Log.d("MyFirebaseMessagingService", token)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null) {
            val title = message.notification?.title.toString()
            val content = message.notification?.body.toString()
            val time = SimpleDateFormat("yyyy-MM-dd").format(message.sentTime)
            Log.d("MyFirebaseMessagingService", title)
            Log.d("MyFirebaseMessagingService", content)
            Log.d("MyFirebaseMessagingService", time.toString())

            setNotification(title, content)

            repository.addNotice(
                NoticeEntity(
                    title = title,
                    content = content,
                    time = time
                )
            )
        }
    }

    private fun setNotification(title: String, content: String) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, "channel").apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(content)
            setAutoCancel(true)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            setContentIntent(pendingIntent)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                "channel",
                "channelName",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                enableLights(true)
                enableVibration(true)
                lightColor = Color.GREEN
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
                notificationManager.createNotificationChannel(this)
            }
        }
        notificationManager.notify(0, notificationBuilder.build())
    }
}