package dev.redfox.newsapp.networking

import dev.redfox.newsapp.models.News
import retrofit2.Response


class Repository {
    suspend fun getLatestNews(category:String): Response<News>{
        return RetrofitInstance.api.getNews(category)
    }

}