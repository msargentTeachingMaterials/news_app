package com.example.rkjc.news_app_2.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.lifecycle.LiveData
import androidx.room.Delete


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
