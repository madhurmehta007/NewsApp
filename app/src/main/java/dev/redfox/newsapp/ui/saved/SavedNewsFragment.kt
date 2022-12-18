package dev.redfox.newsapp.ui.saved

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.redfox.newsapp.adapters.SavedNewsAdapter
import dev.redfox.newsapp.database.*
import dev.redfox.newsapp.databinding.FragmentSavedNewsBinding
import dev.redfox.newsapp.models.Data
import dev.redfox.newsapp.ui.home.NewsDetailFragment


class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private lateinit var newsDBViewModel: NewsDBViewModel
    private lateinit var savedNewsViewModel: SavedNewsViewModel
    private lateinit var savedNewsAdapter: SavedNewsAdapter
    private val binding get() = _binding!!

    private val database by lazy {
        NewsRoomDatabase.getNewsDatabase(requireContext())
    }

    private val newsRepository: NewsRepository by lazy {
        NewsRepository(database.newsDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         savedNewsViewModel =
            ViewModelProvider(this).get(SavedNewsViewModel::class.java)
        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)

        newsDBViewModel = ViewModelProvider(this,NewsDBViewModelFactory(newsRepository))[NewsDBViewModel::class.java]

        newsDBViewModel.allNewsLists.observe(viewLifecycleOwner, Observer {
            val newsData: MutableList<News> = it

            savedNewsAdapter = SavedNewsAdapter(newsDBViewModel,requireContext(),newsData)

            var adapter = savedNewsAdapter

            adapter.notifyDataSetChanged()
            binding.rvSavedNews.adapter = savedNewsAdapter
            binding.rvSavedNews.setHasFixedSize(true)
            binding.rvSavedNews.layoutManager = GridLayoutManager(context, 2)
            adapter.notifyDataSetChanged()


            savedNewsAdapter.onItemClick = {
                val dialog = SavedNewsDetailFragment(it)
                dialog.setCancelable(true)
                dialog.show(parentFragmentManager, "NewsBottomSheetDialog")
            }

        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}