package com.example.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    private String extraString;

    @Override public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(MainActivity.ACTION_SNOOZE)) {
                    if (intent.hasExtra(MainActivity.EXTRA_STRING)) {
                        extraString = intent.getStringExtra(MainActivity.EXTRA_STRING);
                    }
                }
            }
        }

        Log.i(MainActivity.TAG, "Broadcast receiver has processed the request. " + extraString + " says HI!!");

        startService(context);
    }

    private void startService(Context context) {
        Intent intent = new Intent(context, CustomIntentService.class);
        intent.putExtra(MainActivity.EXTRA_STRING, "Real Dan");
        context.startService(intent);
    }
}
