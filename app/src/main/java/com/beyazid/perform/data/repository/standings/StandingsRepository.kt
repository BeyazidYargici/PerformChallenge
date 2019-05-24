package com.beyazid.perform.data.repository.standings

import androidx.lifecycle.LiveData
import com.beyazid.perform.model.standings.Competition
import com.beyazid.perform.network.ErrorHandler

/**
 *  Created by beyazid on 2019-05-22.
 */
interface StandingsRepository {
    var status: LiveData<ErrorHandler>
    suspend fun getStandings() : LiveData<Competition>
}