package dev.redfox.newsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_list_table")
data class News(
    @PrimaryKey(autoGenerate = true)var id:Long = 0L,
    var title:String ="",
    var content:String="",
    var imageUrl:String="",
    var time:String="",
    var date:String="",
    var readMoreUrl:String="",
    var category:String=""


)
