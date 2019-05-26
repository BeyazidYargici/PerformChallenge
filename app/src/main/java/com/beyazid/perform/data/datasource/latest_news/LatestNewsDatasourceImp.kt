package com.beyazid.perform.data.datasource.latest_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.data.network.ApiService
import com.beyazid.perform.base.BaseRepository
import com.beyazid.perform.data.network.ErrorHandler
import com.beyazid.perform.data.model.latests_news.LatestNewsItem
import com.beyazid.perform.data.model.latests_news.LatestNewsResponse
import com.beyazid.perform.data.network.Status
import com.google.gson.Gson
import xmlToJson
import javax.inject.Inject

/**
 *  This class fetches news from remote or local datasource.
 *  Created by beyazid on 2019-05-22.
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
            val response = apiService.getLatestNewsAsStringAsync().await()
            status.postValue(responseStatusChecker(response))
            val latestNewsResponse = xmlToJson(gson, response.body()!!, LatestNewsResponse::class.java)
            mFetchedLatestNews.postValue(latestNewsResponse.rss?.channel?.item as List<LatestNewsItem>?)
            fetchedLatestNews
        } catch (e: Exception) {
            status.postValue(ErrorHandler(Status.TIME_OUT, 0, Status.TIME_OUT.name))
            fetchedLatestNews
        }

    }
}


