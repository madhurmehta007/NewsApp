package dev.redfox.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class], version = 1)
abstract class NewsRoomDatabase : RoomDatabase(){

    abstract fun newsDao():NewsDao

    companion object {
        @Volatile
        private var INSTANCE:NewsRoomDatabase?=null

        fun getNewsDatabase(context: Context):NewsRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsRoomDatabase::class.java,
                    "News_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}