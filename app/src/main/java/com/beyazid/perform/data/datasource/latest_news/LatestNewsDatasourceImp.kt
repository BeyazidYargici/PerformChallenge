package com.beyazid.perform.data.datasource.latest_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.network.ApiService
import com.beyazid.perform.base.BaseRepository
import com.beyazid.perform.network.ErrorHandler
import com.beyazid.perform.model.latests_news.LatestNewsItem
import com.beyazid.perform.model.latests_news.LatestNewsResponse
import com.google.gson.Gson
import timber.log.Timber
import xmlToJson
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class LatestNewsDatasourceImp @Inject constructor(private val apiService: ApiService, private val gson: Gson) :
    BaseRepository(), LatestNewsDatasource {

    var mFetchedLatestNews = MutableLiveData<List<LatestNewsItem>>()
    override var status = MutableLiveData<ErrorHandler>()

    override val fetchedLatestNews: LiveData<List<LatestNewsItem>>
        get() = mFetchedLatestNews

    /**
     *  This method is responsible for fetching latest news from api and preparing data to be used in datasource
     */
    override suspend fun getLatestNews(): LiveData<List<LatestNewsItem>> {
        return try {
            val newsResponse = apiService.getLatestNewsAsStringAsync().await()
            status.postValue(responseStatusChecker(newsResponse))
            val latestNewsResponse = xmlToJson(gson, newsResponse.body()!!, LatestNewsResponse::class.java)
            mFetchedLatestNews.postValue(latestNewsResponse.rss?.channel?.item as List<LatestNewsItem>?)
            fetchedLatestNews
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            fetchedLatestNews
        }

    }
}


