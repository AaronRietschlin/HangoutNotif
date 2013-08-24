package com.asa.notif.hangouts.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.asa.notif.hangouts.AppData;

/**
 * Created by Aaron on 7/25/13.
 */
public class HangoutNotificationListenerService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // TODO - Use a ContentProvider to store the notifications.
        // TODO - Use the "addWatchContentUris(String[]) call in teh extension class to watch for data changes.
        Log.d("", "");
        Notification notif = sbn.getNotification();
        int id = sbn.getId();
        String packageName = sbn.getPackageName();
        if (TextUtils.equals(packageName, AppData.HANGOUTS_PACKAGE_NAME)) {
            handleNotification();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        PendingIntent pIntent = notif.contentIntent;
    }

    private void handleNotification() {
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d("", "");
    }
}
