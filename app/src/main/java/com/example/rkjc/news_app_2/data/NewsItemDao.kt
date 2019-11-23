package com.example.rkjc.news_app_2.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import java.util.Queue

@Dao
interface NewsItemDao {

    @Query("SELECT * FROM news_item ORDER BY time")
    fun loadAllNewsItems(): LiveData<List<NewsItem>>

    @Insert
    fun insert(newsItem: NewsItem)

    @Insert
    fun insert(items: List<NewsItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(taskEntry: NewsItem)

    @Delete
    fun delete(newsItem: NewsItem)

    @Query("SELECT * FROM news_item WHERE id = :id")
    fun loadTaskById(id: Int): LiveData<NewsItem>

    @Query("DELETE FROM news_item")
    fun clearItems()
}
