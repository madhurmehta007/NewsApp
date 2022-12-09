package dev.redfox.newsapp.models

data class News(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)