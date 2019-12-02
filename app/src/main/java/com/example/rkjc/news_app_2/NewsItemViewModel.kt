package com.example.rkjc.news_app_2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class NewsItemViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _newsItems = MutableLiveData<List<NewsItem>>()

    val newsItems: LiveData<List<NewsItem>>
    get() = _newsItems

    init{
        getNewsItems()
    }

    private var _status = MutableLiveData<String>()

    private fun getNewsItems(){
        coroutineScope.launch{
            var getNewsItemsDeferred = NewsApi.retrofitService.getNewsItems()
            try{
                var data = getNewsItemsDeferred.await()
                _newsItems.value = data.articles
                val item : NewsItem? = data?.articles?.get(0)
                _status.value = "Success: ${data?.articles?.size} news items"
                Log.i("NewsItemViewModel", "Success: ${data?.articles?.size} news items")
                Log.i("NewsItemViewModel", "Title: " + item?.title)

            }catch (e: Exception){
                Log.e("NewsItemViewModel", "Failure: " + e.message)
                _status.value = "Failure: " + e.message
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