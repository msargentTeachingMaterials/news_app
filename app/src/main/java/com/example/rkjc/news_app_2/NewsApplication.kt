package com.example.rkjc.news_app_2

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.*
import com.example.rkjc.news_app_2.Work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class NewsApplication: Application(){
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "application started")
        delayedInit()
    }

    private fun delayedInit(){
        applicationScope.launch{
            Log.i("Application", "Work request for sync is run")
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork(){
        // Pass in the repeat interval and unit

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }
                .build()
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.MINUTES)
                //.setConstraints(constraints)
                .build()
        WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDataWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
        )
    }
}