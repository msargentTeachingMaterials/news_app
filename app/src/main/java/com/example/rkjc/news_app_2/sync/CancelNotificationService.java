package com.example.rkjc.news_app_2.sync;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

public class CancelNotificationService extends IntentService {

    public CancelNotificationService() {
        super("IntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        NotificationUtils.clearAllNotifications(this);
    }
}
