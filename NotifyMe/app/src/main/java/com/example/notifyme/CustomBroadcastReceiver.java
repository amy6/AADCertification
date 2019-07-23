package com.example.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    @Override public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(MainActivity.ACTION_SNOOZE)) {
                    if (intent.hasExtra(MainActivity.EXTRA_STRING)) {
                        String extraString = intent.getStringExtra(MainActivity.EXTRA_STRING);
                        Log.i(MainActivity.TAG, "Broadcast receiver has processed the request. " + extraString + " says HI!!");
                        updateNotification(context);

                        startService(context);
                    }
                } else if (intent.getAction().equals(MainActivity.ACTION_CANCEL)) {
                    Log.i(MainActivity.TAG, "Broadcast receiver has processed the request. Notification has been cancelled.");

                    cancelNotification(context);
                }
            }
        }
    }

    private void cancelNotification(Context context) {
        NotificationManagerCompat.from(context).cancel(MainActivity.NOTIFICATION_ID);
    }

    private void updateNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Ooh cool! I've been updated.")
                .setSmallIcon(R.drawable.ic_android)
                .setContentText("Great! You finally actually did something.")
                .setAutoCancel(true);
        NotificationManagerCompat.from(context).notify(MainActivity.NOTIFICATION_ID, builder.build());
    }

    private void startService(Context context) {
        Intent intent = new Intent(context, CustomIntentService.class);
        intent.putExtra(MainActivity.EXTRA_STRING, "Real Dan");
        context.startService(intent);
    }
}
