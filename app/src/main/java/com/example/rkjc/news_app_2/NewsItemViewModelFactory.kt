package com.example.rkjc.news_app_2

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsItemViewModelFactory(val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsItemViewModel::class.java)) {
            return NewsItemViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}