package com.beyazid.perform.ui.latest_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.data.network.ErrorHandler
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepository
import com.beyazid.perform.data.model.latests_news.LatestNewsItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class LatestNewsViewModel @Inject constructor(private val latestNewsRepository: LatestNewsRepository) : ViewModel() {


    var status: LiveData<ErrorHandler>? = null
    var latestNewsResponse = MutableLiveData<List<LatestNewsItem>>()

    /**
     *  Feeds UI with fetched data from repository
     */
    fun getLatestNews() = viewModelScope.launch {
        latestNewsResponse.value = latestNewsRepository.getLatestNews().value
        status = latestNewsRepository.status
    }

}
