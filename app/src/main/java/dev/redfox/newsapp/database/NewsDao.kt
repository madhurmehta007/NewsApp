package dev.redfox.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_list_table")
    fun getAllNews(): LiveData<MutableList<News>>

    @Insert
    fun insertNews(vararg attendance: News)

    @Delete
    fun deleteNews(vararg attendance: News)

    @Update
    fun updateNews(vararg attendance: News)
}