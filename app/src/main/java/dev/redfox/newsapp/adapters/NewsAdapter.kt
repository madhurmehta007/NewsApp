package dev.redfox.newsapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.redfox.newsapp.R
import dev.redfox.newsapp.databinding.ItemHomeNewsBinding
import dev.redfox.newsapp.models.Data
import dev.redfox.newsapp.ui.home.HomeViewModel
import dev.redfox.newsapp.ui.home.NewsDetailFragment

class NewsAdapter(
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

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        holder.binding.tvNews.text = news.title
        Picasso.get().load(news.imageUrl).into(holder.binding.newsImage)

//        holder.itemView.setOnClickListener(object :View.OnClickListener{
//
//            @SuppressLint("ResourceType")
//            override fun onClick(v: View?) {
//                val activity=v!!.context as AppCompatActivity
//                val newsDetailFragment=NewsDetailFragment(news)
//                activity.supportFragmentManager.beginTransaction().replace(R.id.homeContainer,newsDetailFragment).addToBackStack(null).commit()
//
//            }
//        })

        holder.binding.tvNews.setOnClickListener(){
            onItemClick?.invoke(news)
        }

        holder.binding.cvNews.setOnClickListener(){
            onItemClick?.invoke(news)
        }

        holder.binding.ivSaveBorder.setOnClickListener {
            holder.binding.ivSaveBorder.visibility = View.GONE
            holder.binding.ivSave.visibility = View.VISIBLE
        }

        holder.binding.ivSave.setOnClickListener {
            holder.binding.ivSave.visibility = View.GONE
            holder.binding.ivSaveBorder.visibility = View.VISIBLE
        }


    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}