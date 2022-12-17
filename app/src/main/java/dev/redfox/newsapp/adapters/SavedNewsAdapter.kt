package dev.redfox.newsapp.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.squareup.picasso.Picasso
import dev.redfox.newsapp.R
import dev.redfox.newsapp.database.News
import dev.redfox.newsapp.database.NewsDBViewModel
import dev.redfox.newsapp.databinding.FragmentSavedNewsBinding
import dev.redfox.newsapp.databinding.ItemSavedNewsBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class SavedNewsAdapter(
    val newsDBViewModel: NewsDBViewModel,
    val context: Context,
    val newsList: MutableList<News>
) :
    RecyclerView.Adapter<SavedNewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSavedNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding, parent.context)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]

        Picasso.get().load(item.imageUrl).into(holder.binding.newsImage)
        holder.binding.tvDate.text = item.date
        holder.binding.tvNews.text = item.title

        holder.binding.cvSavedNews.animation = android.view.animation.AnimationUtils.loadAnimation(holder.itemView.context,
            R.anim.setting_anim)
    }

    class NewsViewHolder(val binding: ItemSavedNewsBinding, context: Context) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


}