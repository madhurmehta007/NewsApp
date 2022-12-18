package dev.redfox.newsapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dev.redfox.newsapp.adapters.NewsAdapter
import dev.redfox.newsapp.database.NewsDBViewModel
import dev.redfox.newsapp.database.NewsDBViewModelFactory
import dev.redfox.newsapp.database.NewsRepository
import dev.redfox.newsapp.database.NewsRoomDatabase
import dev.redfox.newsapp.databinding.FragmentHomeBinding
import dev.redfox.newsapp.models.Data
import dev.redfox.newsapp.networking.Repository
import kotlinx.coroutines.*

private lateinit var homeViewModel: HomeViewModel
private lateinit var newsDBViewModel: NewsDBViewModel
private lateinit var newsAdapter: NewsAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val repository: Repository by lazy {
        Repository()
    }

    private val database by lazy {
        NewsRoomDatabase.getNewsDatabase(requireContext())
    }

    private val newsRepository: NewsRepository by lazy {
        NewsRepository(database.newsDao())
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        newsDBViewModel =
            ViewModelProvider(
                this,
                NewsDBViewModelFactory(newsRepository)
            )[NewsDBViewModel::class.java]
        homeViewModel.getNews("all")
        DisplayNews()

        binding.cvAll.setOnClickListener {
            homeViewModel.getNews("all")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvNational.setOnClickListener {
            homeViewModel.getNews("national")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvBusiness.setOnClickListener {
            homeViewModel.getNews("business")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvSports.setOnClickListener {
            homeViewModel.getNews("sports")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvWorld.setOnClickListener {
            homeViewModel.getNews("world")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvEntertainment.setOnClickListener {
            homeViewModel.getNews("entertainment")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvPolitics.setOnClickListener {
            homeViewModel.getNews("politics")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvScience.setOnClickListener {
            homeViewModel.getNews("science")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        binding.cvTech.setOnClickListener {
            homeViewModel.getNews("technology")
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    DisplayNews()
                }
            }
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun DisplayNews() {

        var loadProgress = binding.shimmerEffectHome
        homeViewModel.newsResponse.observe(viewLifecycleOwner, Observer {
            val nData: MutableList<Data> = it.body()?.data as MutableList<Data>

            loadProgress.visibility = View.GONE
            Log.d("statusCode", it.code().toString())

            newsAdapter = NewsAdapter(newsDBViewModel, requireContext(), nData)

            var adapter = newsAdapter

            adapter.notifyDataSetChanged()
            binding.rvNewsHome.setHasFixedSize(true)
            binding.rvNewsHome.adapter = adapter
            binding.rvNewsHome.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()


            newsAdapter.onItemClick = {
                val dialog = NewsDetailFragment(it)
                dialog.setCancelable(true)
                dialog.show(parentFragmentManager, "NewsBottomSheetDialog")
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}