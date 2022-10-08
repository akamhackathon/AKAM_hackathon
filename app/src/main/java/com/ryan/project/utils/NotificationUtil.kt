package com.ryan.project.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ryan.project.R
import javax.inject.Inject

class NotificationUtil @Inject constructor(
    val context: Context
){
    fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
                .apply {
                    lightColor = Color.WHITE
                    enableLights(true)
                }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title: String, contentText: String, notificationId: Int) {
        val notification = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_baseline_person_outline_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, notification)
    }
}