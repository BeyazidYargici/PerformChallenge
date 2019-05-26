package com.beyazid.perform.ui.news_detail


import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import com.beyazid.perform.R
import kotlinx.android.synthetic.main.fragment_news_detail.*

/**
 *  This fragment shows the detail of the news in a WebView
 */
class NewsDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_news_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val detailUrl = arguments?.getString("news_detail_url")
        val webView = fr_news_detail_wv
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.loadsImagesAutomatically = true
        webView.loadUrl(detailUrl)
    }
}
