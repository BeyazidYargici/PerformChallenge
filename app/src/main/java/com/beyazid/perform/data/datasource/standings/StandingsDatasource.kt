package com.beyazid.perform.data.datasource.standings

import androidx.lifecycle.LiveData
import java.util.*

/**
 *  Created by beyazid on 2019-05-22.
 */
interface StandingsDatasource {
    val fetchedStandings: LiveData<Objects>
    suspend fun getStandings() : LiveData<Objects>
}