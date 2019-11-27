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
        private var INSTANCE: NewsItemRoomDatabase? = null

        fun getDatabase(context: Context): NewsItemRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(NewsItemRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                NewsItemRoomDatabase::class.java, "word_database")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
