package com.beyazid.perform.ui.latest_news

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beyazid.perform.R
import com.beyazid.perform.model.latests_news.LatestNewsItem
import com.beyazid.perform.utils.GlideApp
import kotlinx.android.synthetic.main.row_latest_news.view.*

/**
 *  Created by beyazid on 2019-05-21.
 */
class LatestNewsAdapter(val context: Context,val newsList : List<LatestNewsItem>) : RecyclerView.Adapter<LatestNewsAdapter.LatestNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestNewsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_latest_news, parent, false)
        return LatestNewsViewHolder(v)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: LatestNewsViewHolder, position: Int) {
        holder.bindItem(position)
    }

    inner class LatestNewsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTitle = view.row_latest_news_tv_title
        val tvDate =view.row_latest_news_tv_date
        val ivImage = view.row_latest_news_iv_image

        fun bindItem(pos : Int){
            val news = newsList[pos]
            tvTitle.text = news.title
            tvDate.text = news.pubDate
            GlideApp.with(ivImage.context).load(news.enclosure?.url).into(ivImage)
        }
    }
}