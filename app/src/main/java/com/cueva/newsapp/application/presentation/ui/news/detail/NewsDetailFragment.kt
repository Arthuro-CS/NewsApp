package com.cueva.newsapp.application.presentation.ui.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cueva.newsapp.R


class NewsDetailFragment : Fragment() {

    val args: NewsDetailFragmentArgs by navArgs()

    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_news_detail, container, false)

        progressBar = rootView.findViewById(R.id.progressBar)

        val url = args.urlSelected
        val view: WebView = rootView.findViewById(R.id.webview)
        view.settings.javaScriptEnabled = true
        view.webViewClient = WebViewClient()
        view.loadUrl(url)


        return rootView
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }
}