package com.example.rkjc.news_app_2.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rkjc.news_app_2.JsonUtils;
import com.example.rkjc.news_app_2.NetworkUtils;

import java.io.IOException;
import java.util.List;

public class NewsItemRepository {
    private static NewsItemRepository INSTANCE;
    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;
    private List<NewsItem> responseItems;


    private NewsItemRepository(Application application){
        NewsItemRoomDatabase db = NewsItemRoomDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao = db.newsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    public static NewsItemRepository getInstance(Application application){
        if(INSTANCE == null){
            INSTANCE = new NewsItemRepository(application);
        }
        return INSTANCE;
    }
    
    LiveData<List<NewsItem>> getmAllNewsItems(){
        return mAllNewsItems;
    }
    
    public void syncWithAPI(){
        Log.d("mycode", "syncWithAPI() called");
        new ResponseAsyncTask().execute();

    }

    private class ResponseAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String responseString = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
                Log.d("mycode", responseString);
                responseItems = JsonUtils.parseNews(responseString);
                mNewsItemDao.clearItems();
                mNewsItemDao.insert(responseItems);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
