package com.beyazid.perform.data.repository.latest_news

import androidx.lifecycle.LiveData
import com.beyazid.perform.network.ErrorHandler
import com.beyazid.perform.data.datasource.latest_news.LatestNewsDatasource
import com.beyazid.perform.model.latests_news.LatestNewsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class LatestNewsRepositoryImp @Inject constructor(private val latestNewsDatasource: LatestNewsDatasource) :
    LatestNewsRepository {
    override var status: LiveData<ErrorHandler>
        get() = latestNewsDatasource.status
        set(value) {}

    override suspend fun getLatestNews(): LiveData<List<LatestNewsItem>> {
        return withContext(Dispatchers.IO) {
            latestNewsDatasource.getLatestNews()
        }
    }
}