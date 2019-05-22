package com.beyazid.perform.ui.latest_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.model.latests_news.LatestNewsResponse
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepository
import com.beyazid.perform.model.latests_news.LatestNewsItem
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class LatestNewsViewModel @Inject constructor(private val latestNewsRepository: LatestNewsRepository) : ViewModel() {


    var latestNewsResponse: LiveData<List<LatestNewsItem>>? = null

    fun getLatestNews() = viewModelScope.launch {
        latestNewsResponse = latestNewsRepository.getLatestNews()
    }

}
