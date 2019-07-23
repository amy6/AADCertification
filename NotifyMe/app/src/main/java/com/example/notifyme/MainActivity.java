package com.example.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String ACTION_SNOOZE = "snooze-action";
    public static final String EXTRA_STRING = "extra-string";
    private static final String NOTIFICATION_CHANNEL_ID = "default-notification-channel";

    private static final int NOTIFICATION_ID = 123;
    private static final int REQUEST_CODE = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notifyButton = findViewById(R.id.notify_button);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void createNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Default", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(android.R.color.holo_purple);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("I'm here to notify you")
                .setContentText("This is your notification message. Now go do something productive!")
                .setSmallIcon(R.drawable.ic_android)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setStyle(createNotificationStyle())
                .addAction(createAction())
                .setContentIntent(createPendingIntent());

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private NotificationCompat.Action createAction() {
        Intent intent = new Intent(this, CustomBroadcastReceiver.class);
        intent.setAction(ACTION_SNOOZE);
        intent.putExtra(EXTRA_STRING, "Harry");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Action(R.drawable.ic_android, "Snooze", pendingIntent);
    }

    private NotificationCompat.Style createNotificationStyle() {
        return new NotificationCompat.BigTextStyle()
                .bigText("This is like a big big text. You know. Like a paragraph. Where you don't have the 160 character limit. Or wait, may be there is..!");
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        return PendingIntent.getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
