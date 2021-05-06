package com.cueva.newsapp.application.presentation.ui.news.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cueva.newsapp.R
import com.cueva.newsapp.application.presentation.di.DaggerNewsComponent
import com.cueva.newsapp.databinding.FragmentNewsListBinding
import javax.inject.Inject


class NewsListFragment : Fragment() {

    @Inject
    lateinit var newsViewModel: NewsViewModel

    lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news_list, container, false
        )

        DaggerNewsComponent.create().inject(this)

        val adapter = NewsAdapter()
        binding.rvLeagueList.adapter = adapter


        binding.swipeRefreshLayout.setOnRefreshListener {
            loadNews()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        loadNews()

        newsViewModel.newsList.observe(viewLifecycleOwner){
            it?.let {
                binding.progressBarList.visibility = ProgressBar.GONE
                binding.rvLeagueList.visibility = RecyclerView.VISIBLE
                adapter.submitList(it)
            }
        }

        binding.setLifecycleOwner(this)
        return binding.root
    }

    fun loadNews(){
        binding.progressBarList.visibility = ProgressBar.VISIBLE
        binding.rvLeagueList.visibility = RecyclerView.GONE
        newsViewModel.getNews()
    }
}