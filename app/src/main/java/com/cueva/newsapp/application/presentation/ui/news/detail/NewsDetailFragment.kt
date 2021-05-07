package com.cueva.newsapp.application.presentation.ui.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cueva.newsapp.R


class NewsDetailFragment : Fragment() {

    val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_news_detail, container, false)
        val url = args.urlSelected
        val view : WebView =  rootView.findViewById(R.id.webview)
        view.settings.javaScriptEnabled = true
        view.loadUrl(url)


        return rootView
    }
}