package com.beyazid.perform.data.datasource.standings

import androidx.lifecycle.LiveData
import com.beyazid.perform.model.standings.Competition
import java.util.*

/**
 *  Created by beyazid on 2019-05-22.
 */
interface StandingsDatasource {
    val fetchedStandings: LiveData<Competition>
    suspend fun getStandings() : LiveData<Competition>
}