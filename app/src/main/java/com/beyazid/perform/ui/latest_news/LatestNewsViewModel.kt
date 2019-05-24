package com.beyazid.perform.ui.latest_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.network.ErrorHandler
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepository
import com.beyazid.perform.model.latests_news.LatestNewsItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class LatestNewsViewModel @Inject constructor(private val latestNewsRepository: LatestNewsRepository) : ViewModel() {


    var status: LiveData<ErrorHandler>? = null
    var latestNewsResponse: LiveData<List<LatestNewsItem>>? = null

    fun getLatestNews() = viewModelScope.launch {
        latestNewsResponse = latestNewsRepository.getLatestNews()
        status = latestNewsRepository.status
    }

}
