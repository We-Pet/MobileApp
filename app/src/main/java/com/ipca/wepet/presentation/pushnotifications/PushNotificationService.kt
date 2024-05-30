package com.ipca.wepet.presentation.pushnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ipca.wepet.R
import com.ipca.wepet.presentation.controller.HomePageActivity
import com.ipca.wepet.presentation.views.WePetSplashScreenActivity

class PushNotificationService : FirebaseMessagingService() {


    // Override onNewToken to get new token
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    // Override onMessageReceived() method to extract the title body from the message passed in FCM
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.getNotification() != null) {
            remoteMessage.getNotification()!!.title?.let {
                remoteMessage.getNotification()!!.body?.let { it1 ->
                    showNotification(
                        it, it1
                    )
                }
            }
        }
    }

    private fun getCustomDesign(title: String, message: String): RemoteViews {
        val remoteViews = RemoteViews(packageName, R.layout.notification)
        remoteViews.setTextViewText(R.id.tittle, title)
        remoteViews.setTextViewText(R.id.description, message)
        remoteViews.setImageViewResource(R.id.app_logo, R.drawable.main_logo)
        return remoteViews
    }

    private fun showNotification(title: String, message: String) {
        val intent = Intent(this, HomePageActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val channelId = "notification_channel"
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.main_icon)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setContent(getCustomDesign(title, message))

        val notificationManager = ContextCompat.getSystemService(
            this, NotificationManager::class.java
        ) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId, "web_app", NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())
    }
}