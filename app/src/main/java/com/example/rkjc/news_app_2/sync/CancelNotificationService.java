package com.example.rkjc.news_app_2.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class CancelNotificationService extends IntentService {

    public CancelNotificationService() {
        super("IntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        NotificationUtils.clearAllNotifications(this);
    }
}
