/**
 * @author based on tutorial
 *
 * References:
 * @see source: Make it Easy (YouTube) -
 * "Firebase Cloud Message(FCM) Push Notification in Jetpack Compose | Android | Kotlin | Make it Easy"
 *  https://www.youtube.com/watch?v=T_vnqMk2QxM
 *  (mostly this one)
 *
 * @see source: kenmaro's (YouTube) -
 * "[JetpackCompose] Firebase Cloud Messaging to Send Push Notification"
 *  https://www.youtube.com/watch?v=tSlE-OfCV40
 */

package com.example.ukuleletuner2.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.ukuleletuner2.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * firebase cloud messaging service for push notifications
 *
 *What it does:
 *-process incoming messages and display them
 *-handle firebase tokens
 *-takes care of notification channels
 */
class FirebaseMessagingService : FirebaseMessagingService() {

    /**
     * companion object static member inside a class (singleton object that belongs this class)
     */
    companion object {
        private const val TAG = "FCM Notification"
        const val DEFAULT_NOTIFICATION_ID = 0
        private const val CHANNEL_ID = "fcm_messages"
    }

/**
 * (notice that override android calls this methods by itself I don't call them in my code)
 *
 * Called when a new FCM registration token is generated.
 *
 * (here doesn't do much)
 *
 * when is new token generated:
 * - app's first installed
 * - user data is cleared
 * - token is refreshed (security etc.)
 *
 * normally should be sent server
 *
 * @param token the new firebase token for this device
 */
    override fun onNewToken(token: String) {
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }

    /**
     * (notice that override android calls this methods by itself I don't call them in my code)
     *
     * Called when an firebase message is received while the app on FOREGROUND
     * (when on background android handles notifications automatically)
     *
     * -gets notification
     * -calls showNotification to show the notification
     *
     * @param remoteMessage message from firebase
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title =
            remoteMessage.notification?.title ?: getString(R.string.default_notification_name)
        val body =
            remoteMessage.notification?.body ?: getString(R.string.default_notification_message)

        showNotification(title, body)
    }

    /**
     * shows notification to user when on FOREGROUND
     * (when on foreground this action needs to be explicitly handled)
     *
     * Creates and shows a notification using Android's NotificationCompat
     *
     * @param title what to show as title
     * @param body what to show as body
     */
    private fun showNotification(title: String, body: String) {
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        initNotificationChannel(notificationManager)

        //.setAutoCancel(true) - terminate when tapped
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        //this shows notification to user
        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notification)
    }

    /**
     * Creates notification channel
     *
     * (from android 8+ notification are basically split to channels so users can better control them
     * and choice witch which want
     *
     * like:
     * -promo
     * -messages
     * -reminders
     * )
     *
     * @param notificationManager  system notification manager
     */
    private fun initNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannelIfNotExists(
                channelId = CHANNEL_ID,
                channelName = getString(R.string.firebase_channal_name)
            )
        }
    }
}
/**
* Checks it notification channel exists to prevent duplicates and if not creates it
*
* @param channelId ID for the notification channel
* @param channelName  name for the channel
* @param importance notification importance level
*/
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