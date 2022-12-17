package dev.redfox.newsapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.redfox.newsapp.R
import dev.redfox.newsapp.database.*
import dev.redfox.newsapp.databinding.ItemHomeNewsBinding
import dev.redfox.newsapp.models.Data
import dev.redfox.newsapp.utils.Snacker


private lateinit var newsRepository: NewsRepository

class NewsAdapter(
val newsDBViewModel: NewsDBViewModel,
val context: Context,
    val newsList:MutableList<Data>
    ):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var onItemClick : ((Data) -> Unit)? = null

        class NewsViewHolder(val binding:ItemHomeNewsBinding,context: Context):RecyclerView.ViewHolder(binding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeNewsBinding.inflate(layoutInflater, parent, false)

        return NewsViewHolder(binding, parent.context)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news = newsList[position]

        holder.binding.tvNews.text = news.title
        Picasso.get().load(news.imageUrl).into(holder.binding.newsImage)
        holder.binding.tvDate.text = news.date

        holder.binding.tvNews.setOnClickListener(){
            onItemClick?.invoke(news)
        }

        holder.binding.cvNews.setOnClickListener(){
            onItemClick?.invoke(news)
        }

        holder.binding.ivSaveBorder.setOnClickListener {
            holder.binding.ivSaveBorder.visibility = View.GONE
            holder.binding.ivSave.visibility = View.VISIBLE

            val newsData = News(
                title = news.title,
                content = news.content,
                imageUrl = news.imageUrl,
                time = news.time,
                date = news.date,
                readMoreUrl = news.readMoreUrl
            )
                newsDBViewModel.insertNews(newsData)
            Snacker(it,"News saved to your list").success()

        }

        holder.binding.ivSave.setOnClickListener {
            holder.binding.ivSave.visibility = View.GONE
            holder.binding.ivSaveBorder.visibility = View.VISIBLE
        }

        holder.binding.cvHomeNews.animation = android.view.animation.AnimationUtils.loadAnimation(holder.itemView.context,R.anim.setting_anim)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}