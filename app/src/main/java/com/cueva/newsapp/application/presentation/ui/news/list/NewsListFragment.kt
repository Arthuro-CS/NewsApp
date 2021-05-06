package com.cueva.newsapp.application.presentation.ui.news.list

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cueva.newsapp.R
import com.cueva.newsapp.application.presentation.di.DaggerNewsComponent
import com.cueva.newsapp.application.presentation.di.RoomModule
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

        DaggerNewsComponent.builder().roomModule(RoomModule(requireActivity().application))
            .build().inject(this)

        val adapter = NewsAdapter(NewsAdapter.OnClickListener { url ->
            navigateToDetail(url)
        })
        binding.rvLeagueList.adapter = adapter


        binding.swipeRefreshLayout.setOnRefreshListener {
            loadNews(isOnline(requireContext()))
            binding.swipeRefreshLayout.isRefreshing = false
        }

        loadNews(isOnline(requireContext()))

        newsViewModel.newsList.observe(viewLifecycleOwner) {
            it?.let {
                binding.progressBarList.visibility = ProgressBar.GONE
                binding.rvLeagueList.visibility = RecyclerView.VISIBLE
                adapter.submitList(it)
            }
        }

        binding.setLifecycleOwner(this)
        return binding.root
    }

    private fun navigateToDetail(url: String) {
        if (!isOnline(requireContext())) {
            Toast.makeText(requireContext(), "INTERNET CONNECTION REQUIRED", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(url)
        findNavController().navigate(action)
    }

    fun loadNews(isOnline: Boolean) {
        binding.progressBarList.visibility = ProgressBar.VISIBLE
        binding.rvLeagueList.visibility = RecyclerView.GONE
        newsViewModel.getNewsList(isOnline)
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null)
            return true

        return false
    }
}