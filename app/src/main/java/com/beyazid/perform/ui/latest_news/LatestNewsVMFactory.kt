package com.beyazid.perform.ui.latest_news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepository
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */
class LatestNewsVMFactory @Inject constructor(private val latestNewsRepository: LatestNewsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LatestNewsViewModel(latestNewsRepository) as T
    }
}