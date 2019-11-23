package com.example.rkjc.news_app_2;

import android.util.Log;

import com.example.rkjc.news_app_2.data.NewsItem;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "### JsonUtils ###";

    public static List<NewsItem> parseNews(String jsonString){
        List<NewsItem> newsItemList = new ArrayList<>();
        try {
            JSONObject topLevelObject = new JSONObject(jsonString);
            JSONArray itemsArray = topLevelObject.getJSONArray("articles");


            for(int i = 0; i < itemsArray.length(); i++){
                JSONObject itemObject = itemsArray.getJSONObject(i);
                String title = itemObject.getString("title");
                String description = itemObject.getString("description");
                String time = itemObject.getString("publishedAt");
                String url = itemObject.getString("url");
                String imageUrl = itemObject.getString("urlToImage");
                NewsItem newsItem = new NewsItem();
                newsItem.setTitle(title);
                newsItem.setDescription(description);
                newsItem.setTime(time);
                newsItem.setUrl(url);
                newsItem.setThumbURL(imageUrl);
                newsItemList.add(newsItem);
            }

        }
        catch(org.json.JSONException e){
            e.printStackTrace();
        }

        return newsItemList;
    }
}


