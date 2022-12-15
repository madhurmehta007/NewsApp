package dev.redfox.newsapp.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NewsRepository(private val newsDao: NewsDao) {
    val allNewsLists:LiveData<MutableList<News>> = newsDao.getAllNews()


    @WorkerThread
    fun insertNews(news: News) {
        newsDao.insertNews(news)
    }

    @WorkerThread
    fun deleteNews(news: News) {
        newsDao.deleteNews(news)
    }

    @WorkerThread
    fun updateNews(news: News) {
        newsDao.updateNews(news)
    }
}