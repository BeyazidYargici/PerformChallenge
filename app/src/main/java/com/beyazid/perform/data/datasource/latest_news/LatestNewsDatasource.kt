package com.beyazid.perform.data.datasource.latest_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.network.ErrorHandler
import com.beyazid.perform.model.latests_news.LatestNewsItem

/**
 *  Created by beyazid on 2019-05-12.
 */
interface LatestNewsDatasource {
    val fetchedLatestNews: LiveData<List<LatestNewsItem>>
    suspend fun getLatestNews() : LiveData<List<LatestNewsItem>>
    var status: MutableLiveData<ErrorHandler>
}