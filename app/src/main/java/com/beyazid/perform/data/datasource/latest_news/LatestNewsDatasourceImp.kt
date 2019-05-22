package com.beyazid.perform.data.datasource.latest_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.ApiService
import com.beyazid.perform.model.latests_news.LatestNewsItem
import com.beyazid.perform.model.latests_news.LatestNewsResponse
import com.beyazid.perform.model.standings.StandingsResponse
import com.google.gson.Gson
import retrofit2.Response
import timber.log.Timber
import xmlToJson
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class LatestNewsDatasourceImp @Inject constructor(private val apiService: ApiService,private val gson: Gson) : LatestNewsDatasource {

    var mFetchedLatestNews = MutableLiveData<List<LatestNewsItem>>()

    override val fetchedLatestNews: LiveData<List<LatestNewsItem>>
        get() = mFetchedLatestNews

    override suspend fun getLatestNews(): LiveData<List<LatestNewsItem>> {
        return try {
            val xmlResponse= apiService.getLatestNewsAsStringAsync().await()
            val latestNewsResponse = xmlToJson(gson, xmlResponse.body()!!, LatestNewsResponse::class.java)
            mFetchedLatestNews.postValue(latestNewsResponse.rss?.channel?.item as List<LatestNewsItem>?)
            fetchedLatestNews
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            fetchedLatestNews
        }

    }
}

