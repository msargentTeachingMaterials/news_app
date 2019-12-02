package com.example.rkjc.news_app_2.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.lifecycle.LiveData


@Dao
interface NewsItemDao {

    @Query("SELECT * FROM news_item ORDER BY time")
    fun loadAllNewsItems(): LiveData<List<NewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<NewsItem>)

    @Query("DELETE FROM news_item")
    fun clearItems()
}
