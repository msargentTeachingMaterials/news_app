package com.example.rkjc.news_app_2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class Article(
    var articles: List<NewsItem>
)

@Entity(tableName = "news_item")
data class NewsItem (

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "time")
    @Json(name = "publishedAt") var time: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @ColumnInfo(name = "thumbURL")
    @Json(name = "urlToImage") var thumbURL: String? = null

)
