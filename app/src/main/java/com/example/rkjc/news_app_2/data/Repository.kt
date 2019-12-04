package com.example.rkjc.news_app_2.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.rkjc.news_app_2.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: NewsItemRoomDatabase){

    suspend fun refreshNewsItems(){
        withContext(Dispatchers.IO){
            var getNewsItemsDeferred = NewsApi.retrofitService.getNewsItems()
            var data = getNewsItemsDeferred.await()

            database.newsItemDao().clearItems()
            database.newsItemDao().insert(data.articles)

        }
    }

    val newsItems: LiveData<List<NewsItem>> = database.newsItemDao().loadAllNewsItems()
}