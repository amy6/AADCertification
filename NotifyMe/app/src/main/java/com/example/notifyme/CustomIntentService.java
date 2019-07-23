package com.example.notifyme;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class CustomIntentService extends IntentService {

    private static final String TAG = "CustomIntentService";

    public CustomIntentService() {
        super(TAG);
    }

    @Override protected void onHandleIntent(@Nullable Intent intent) {
        String name = "Fake Dan";
        if (intent != null) {
            if (intent.hasExtra(MainActivity.EXTRA_STRING)) {
                name = intent.getStringExtra(MainActivity.EXTRA_STRING);
            }
        }
        try {
            Thread.sleep(3000);
            Log.i(MainActivity.TAG, "Service has handled the request. " + name + " says HELLO!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
