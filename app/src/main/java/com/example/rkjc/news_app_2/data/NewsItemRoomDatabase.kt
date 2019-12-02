package com.example.rkjc.news_app_2.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [NewsItem::class], version = 1, exportSchema = false)
abstract class NewsItemRoomDatabase : RoomDatabase() {
    abstract fun newsItemDao(): NewsItemDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: NewsItemRoomDatabase

        fun getDatabase(context: Context): NewsItemRoomDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(NewsItemRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NewsItemRoomDatabase::class.java, "word_database")
                            .build()
                }
            }
            return INSTANCE
        }
    }
}
