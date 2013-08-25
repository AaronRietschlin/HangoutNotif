package com.asa.notif.hangouts.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Handler;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.asa.notif.hangouts.AppData;
import com.asa.notif.hangouts.R;

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
        notif.defaults = 0;
        int id = sbn.getId();
        String packageName = sbn.getPackageName();
        if (TextUtils.equals(packageName, AppData.HANGOUTS_PACKAGE_NAME)) {
            handleNotification(notif);
        }
    }

    private void handleNotification(Notification notification) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());


        builder.setContentText(notification.tickerText);
        builder.setContentTitle("Test");
        builder.setSmallIcon(R.drawable.ic_launcher);
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification thisNotif = builder.build();
        if (AppData.sUri != null) {
            builder.setSound(AppData.sUri);
        }
        manager.notify(1, thisNotif);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.cancel(1);
            }
        }, 1000);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d("", "");
    }
}
