package com.cueva.newsapp.application.presentation.ui.news.list

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cueva.newsapp.R
import com.cueva.newsapp.application.presentation.di.DaggerNewsComponent
import com.cueva.newsapp.application.presentation.di.RoomModule
import com.cueva.newsapp.application.presentation.di.SharedPreferencesModule
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

        DaggerNewsComponent.builder()
            .sharedPreferencesModule(SharedPreferencesModule(requireActivity().application))
            .roomModule(RoomModule(requireActivity().application))
            .build().inject(this)

        val adapter = NewsAdapter(NewsAdapter.OnClickListener { url ->
            navigateToDetail(url)
        })
        binding.rvNewsList.adapter = adapter


        binding.swipeRefreshLayout.setOnRefreshListener {
            loadNews(isOnline(requireContext()))
            binding.swipeRefreshLayout.isRefreshing = false
        }

        loadNews(isOnline(requireContext()))

        newsViewModel.newsList.observe(viewLifecycleOwner) {
            it?.let {
                binding.progressBarList.visibility = ProgressBar.GONE
                binding.rvNewsList.visibility = RecyclerView.VISIBLE
                adapter.submitList(it)
            }
        }

        configuredSwipteCard(adapter)

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
        binding.rvNewsList.visibility = RecyclerView.GONE
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

    fun configuredSwipteCard(adapter: NewsAdapter) {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    newsViewModel.deleteNews(position)
                    adapter.notifyDataSetChanged()
                }

            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNewsList)
    }
}