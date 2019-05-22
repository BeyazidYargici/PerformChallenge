package com.beyazid.perform.ui.standing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.data.repository.standings.StandingsRepository
import com.beyazid.perform.model.standings.Competition
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */

class StandingsViewModel @Inject constructor(private val standingsRepository: StandingsRepository) : ViewModel() {

    var standingsResponse: LiveData<Competition>? = null

    fun getStandings() = viewModelScope.launch {
        standingsResponse = standingsRepository.getStandings()
    }

}
