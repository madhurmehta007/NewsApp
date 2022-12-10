package dev.redfox.newsapp.ui.home

import dev.redfox.newsapp.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dev.redfox.newsapp.adapters.NewsAdapter
import dev.redfox.newsapp.databinding.FragmentHomeBinding
import dev.redfox.newsapp.models.Data
import dev.redfox.newsapp.networking.Repository

private lateinit var homeViewModel: HomeViewModel
private lateinit var newsAdapter: NewsAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val repository: Repository by lazy {
        Repository()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

         homeViewModel =
            ViewModelProvider(this,HomeViewModelFactory(repository))[HomeViewModel::class.java]

        homeViewModel.getNews("all")
        DisplayNews()

        binding.cvAll.setOnClickListener {
            homeViewModel.getNews("all")
            DisplayNews()
        }

        binding.cvNational.setOnClickListener {
            homeViewModel.getNews("national")
            DisplayNews()
        }

        binding.cvBusiness.setOnClickListener {
            homeViewModel.getNews("business")
            DisplayNews()
        }

        binding.cvSports.setOnClickListener {
            homeViewModel.getNews("sports")
            DisplayNews()
        }

        binding.cvWorld.setOnClickListener {
            homeViewModel.getNews("world")
            DisplayNews()
        }

        binding.cvEntertainment.setOnClickListener {
            homeViewModel.getNews("entertainment")
            DisplayNews()
        }

        binding.cvPolitics.setOnClickListener {
            homeViewModel.getNews("politics")
            DisplayNews()
        }

        binding.cvScience.setOnClickListener {
            homeViewModel.getNews("science")
            DisplayNews()
        }

        binding.cvTech.setOnClickListener {
            homeViewModel.getNews("technology")
            DisplayNews()
        }


        return binding.root
    }

    private fun DisplayNews() {
        homeViewModel.newsResponse.observe(viewLifecycleOwner, Observer {
            val nData: MutableList<Data> = it.body()?.data as MutableList<Data>

            Log.d("statusCode", it.code().toString())

            newsAdapter = NewsAdapter(nData)

            var adapter = newsAdapter

            adapter.notifyDataSetChanged()
            binding.rvNewsHome.setHasFixedSize(true)
            binding.rvNewsHome.adapter = adapter
            binding.rvNewsHome.layoutManager = GridLayoutManager(context, 1)
            adapter.notifyDataSetChanged()


            newsAdapter.onItemClick = {
                val dialog = NewsDetailFragment(it)

//            val bottomSheetView = inflater.inflate(R.layout.fragment_news_detail,null)

                dialog.setCancelable(true)
                dialog.show(parentFragmentManager,"NewsBottomSheetDialog")
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}