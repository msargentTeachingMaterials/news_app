package com.example.rkjc.news_app_2


import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rkjc.news_app_2.data.NewsItem
import com.example.rkjc.news_app_2.data.NewsItemRoomDatabase
import com.example.rkjc.news_app_2.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class NewsItemViewModel(application: Application): ViewModel() {
    private val repository = Repository(NewsItemRoomDatabase.getDatabase(application))
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _newsItems = repository.newsItems

    val newsItems: LiveData<List<NewsItem>>
    get() = _newsItems

    init{
        getNewsItems()
    }

    private fun getNewsItems() {
        coroutineScope.launch {
            try{
                repository.refreshNewsItems()
                Log.d("NewsItemViewModel", "ThumbUrl is null: ${_newsItems.value?.get(0)?.thumbURL == null}")
            }catch(e: Exception){
                Log.e("NewsItemViewModel", e.message)
            }

        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}



//private fun getNewsItems(){
//    NewsApi.retrofitService.getNewsItems().enqueue(
//            object: Callback<Articles> {
//                override fun onFailure(call: Call<Articles>, t: Throwable) {
//                    _response.value = "Failure: " + t.message
//                    Log.e("NewsItemViewModel", "Failure: " + t.message)
//                }
//
//                override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
//                    val item : NewsItem? =  response?.body()?.articles?.get(0)
//                    _response.value = "Success: ${response.body()?.articles?.size} news items"
//                    Log.i("NewsItemViewModel", "Success: ${response.body()?.articles?.size} news items")
//                    Log.i("NewsItemViewModel", "Title: " + item?.title)
//                }
//
//            }
//    )
//}