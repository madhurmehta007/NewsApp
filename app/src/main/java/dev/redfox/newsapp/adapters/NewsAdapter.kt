package dev.redfox.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.redfox.newsapp.databinding.ItemHomeNewsBinding
import dev.redfox.newsapp.models.Data
import dev.redfox.newsapp.ui.home.HomeViewModel

class NewsAdapter(
    val newsList:MutableList<Data>
    ):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        class NewsViewHolder(val binding:ItemHomeNewsBinding,context: Context):RecyclerView.ViewHolder(binding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        holder.binding.tvNews.text = news.title
        Picasso.get().load(news.imageUrl).into(holder.binding.newsImage)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}