package com.example.ukuleletuner2.service


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//https://www.youtube.com/watch?v=T_vnqMk2QxM (full)

class FirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "FCM Notification"
        const val DEFAULT_NOTIFICATION_ID = 0
        private const val CHANNEL_ID = "fcm_messages"
    }

    override fun onNewToken(token: String) {
        Log.i(TAG, "new FCM token created: $token")
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "Ukulele Tuner"
        val body = remoteMessage.notification?.body ?: "New message received"
        val data = remoteMessage.data

        Log.d(TAG, "Message received - Title: $title, Body: $body, Data: $data")

        // Show simple notification
        showNotification(title, body)
    }

    private fun showNotification(title: String, body: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notification)
    }

    private fun initNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannelIfNotExists(
                channelId = CHANNEL_ID,
                channelName = "FCM Messages"
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createNotificationChannelIfNotExists(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    var channel = this.getNotificationChannel(channelId)

    if (channel == null) {
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}