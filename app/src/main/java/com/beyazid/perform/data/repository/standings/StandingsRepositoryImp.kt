package com.beyazid.perform.data.repository.standings

import androidx.lifecycle.LiveData
import com.beyazid.perform.data.datasource.standings.StandingsDatasource
import com.beyazid.perform.data.model.standings.Competition
import com.beyazid.perform.data.network.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */
class StandingsRepositoryImp @Inject constructor(private val standingsDatasource: StandingsDatasource) : StandingsRepository{
    override var status: LiveData<ErrorHandler>
        get() = standingsDatasource.status
        set(value) {}

    /**
     *  This method gets competition from datasource asynchronously
     */
    override suspend fun getStandings(): LiveData<Competition> {
        return withContext(Dispatchers.IO) {
            standingsDatasource.getStandings()
        }
    }
}