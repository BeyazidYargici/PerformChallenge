package com.beyazid.perform.ui.standing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.data.repository.standings.StandingsRepository
import com.beyazid.perform.data.model.standings.Competition
import com.beyazid.perform.data.network.ErrorHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */

class StandingsViewModel @Inject constructor(private val standingsRepository: StandingsRepository) : ViewModel() {

    var status: LiveData<ErrorHandler>? = null
    var standingsResponse: LiveData<Competition>? = null

    /**
     *  Feeds UI with fetched data from repository
     */
    fun getStandings() = viewModelScope.launch {
        standingsResponse = standingsRepository.getStandings()
        status = standingsRepository.status
    }
}
