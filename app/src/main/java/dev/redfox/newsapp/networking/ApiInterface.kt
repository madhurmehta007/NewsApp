package dev.redfox.newsapp.networking

import dev.redfox.newsapp.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("news")
    suspend fun getNews(@Query("category")category: String): Response<News>

}