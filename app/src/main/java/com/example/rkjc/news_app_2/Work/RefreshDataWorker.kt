package com.example.rkjc.news_app_2.Work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.rkjc.news_app_2.data.NewsItemRoomDatabase.Companion.getDatabase
import com.example.rkjc.news_app_2.data.Repository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params){

    companion object{
        const val WORK_NAME = "com.example.rkjc.news_app_2.Work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = Repository(database)
        Log.i("RefreshDataWorker", " Work request for sync is started")
        try{
            repository.refreshNewsItems()
        }catch(e: HttpException){
            return Result.retry()
        }

        return Result.success()
    }

}
