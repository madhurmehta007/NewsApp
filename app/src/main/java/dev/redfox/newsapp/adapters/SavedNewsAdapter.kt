package dev.redfox.newsapp.adapters


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
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
import dev.redfox.newsapp.models.Data
import dev.redfox.newsapp.utils.Snacker
import kotlinx.coroutines.NonDisposableHandle.parent

private lateinit var newsAdapter: SavedNewsAdapter

class SavedNewsAdapter(
    val newsDBViewModel: NewsDBViewModel,
    val context: Context,
    val newsList: MutableList<News>
) :
    RecyclerView.Adapter<SavedNewsAdapter.NewsViewHolder>() {

    var onItemClick:((News) -> Unit)? = null
    var onItemLongClick: ((News) -> Unit)? = null

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

        holder.binding.cvSavedNews.animation = android.view.animation.AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.setting_anim
        )

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton("Yes") { dialog, _ ->
                    newsDBViewModel.deleteNews(item)
                        Snacker(it, "News Deleted").error()
                }
                .setNegativeButton("No") { dialog, _ ->
                        Snacker(it, "News Not Deleted").warning()
                    newsDBViewModel.updateNews(item)
                }
                .setCancelable(false)
                .show()

            true
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }

    }

    class NewsViewHolder(val binding: ItemSavedNewsBinding, context: Context) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


}