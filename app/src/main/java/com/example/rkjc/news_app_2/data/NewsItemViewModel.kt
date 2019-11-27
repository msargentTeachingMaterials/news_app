package com.example.rkjc.news_app_2.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NewsItemViewModel(application: Application) : AndroidViewModel(application) {

    private var mAllNewsItems: LiveData<List<NewsItem>>? = null


    val allNewsItems: LiveData<List<NewsItem>>
        get() {
            val mRepository = NewsItemRepository.getInstance(getApplication())
            return mRepository.getmAllNewsItems()
        }

}
