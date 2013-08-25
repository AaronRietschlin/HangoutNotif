package com.asa.notif.hangouts.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Handler;
import android.os.Looper;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.asa.notif.hangouts.AppData;
import com.asa.notif.hangouts.R;
import com.asa.notif.hangouts.utils.LogUtils;

/**
 * Created by Aaron on 7/25/13.
 */
public class HangoutNotificationListenerService extends NotificationListenerService {
    private static final String TAG = LogUtils.makeLogTag(HangoutNotificationListenerService.class);

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // TODO - Use a ContentProvider to store the notifications.
        // TODO - Use the "addWatchContentUris(String[]) call in teh extension class to watch for data changes.
        LogUtils.LOGD(TAG, "Notification Posted");
        String packageName = sbn.getPackageName();
        if (TextUtils.equals(packageName, AppData.HANGOUTS_PACKAGE_NAME)) {
            handleNotification(sbn.getNotification());
        }
    }

    private void handleNotification(Notification notification) {
        LogUtils.LOGD(TAG, "Handling notification.");

        CharSequence tickerText= notification.tickerText.toString();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

//        builder.setContentText(notification.tickerText).setContentTitle("Test").setSmallIcon(R.drawable.ic_launcher);
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (AppData.sUri != null) {
            builder.setSound(AppData.sUri);
        }
        manager.notify(1, builder.build());
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                manager.cancel(1);
            }
        }, 5000);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d("", "");
    }
}
