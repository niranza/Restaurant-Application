package com.niran.restaurantapplication.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.niran.restaurantapplication.R

object NotificationsUtil {

    fun cancelNotification(context: Context, notificationId: Int) {
        getManager(context).cancel(notificationId)
    }

    fun sendNotification(
        channelIdStringId: Int,
        notificationId: Int,
        titleStringId: Int,
        textStringId: Int,
        intent: Intent,
        context: Context
    ) {

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, context.getString(channelIdStringId))
            .setContentTitle(context.getString(titleStringId))
            .setContentText(context.getString(textStringId))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_food)
            .setContentIntent(pendingIntent)
            .build()

        getManager(context).notify(notificationId, notification)

    }

    fun createNotificationChannel(
        channelIdStringId: Int,
        channelNameStringId: Int,
        context: Context
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                context.getString(channelIdStringId),
                context.getString(channelNameStringId),
                NotificationManager.IMPORTANCE_DEFAULT
            )

            getManager(context).createNotificationChannel(channel)

        }

    }

    private fun getManager(context: Context) =
        ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

}