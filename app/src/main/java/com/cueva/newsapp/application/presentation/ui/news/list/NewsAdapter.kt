package com.cueva.newsapp.application.presentation.ui.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cueva.newsapp.databinding.NewsItemBinding
import com.cueva.newsapp.domain.entity.News

class NewsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    class NewsViewHolder(private var binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.news = news
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(news.storyUrl)
        }
    }

    class OnClickListener(val clickListener: (url: String) -> Unit) {
        fun onClick(url: String) = clickListener(url)
    }
}


class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.storyId == newItem.storyId
    }

}