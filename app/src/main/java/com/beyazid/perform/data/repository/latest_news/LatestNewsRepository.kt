package com.beyazid.perform.data.repository.latest_news

import androidx.lifecycle.LiveData
import com.beyazid.perform.data.network.ErrorHandler
import com.beyazid.perform.data.model.latests_news.LatestNewsItem

/**
 *  Created by beyazid on 2019-05-22.
 */
interface LatestNewsRepository {
    suspend fun getLatestNews() : LiveData<List<LatestNewsItem>>
    var status: LiveData<ErrorHandler>
}