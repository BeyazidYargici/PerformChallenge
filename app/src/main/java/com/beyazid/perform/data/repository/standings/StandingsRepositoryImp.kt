package com.beyazid.perform.data.repository.standings

import androidx.lifecycle.LiveData
import com.beyazid.perform.data.datasource.standings.StandingsDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */
class StandingsRepositoryImp @Inject constructor(private val standingsDatasource: StandingsDatasource) : StandingsRepository{
    override suspend fun getStandings(): LiveData<Objects> {
        return withContext(Dispatchers.IO) {
            standingsDatasource.getStandings()
        }
    }
}