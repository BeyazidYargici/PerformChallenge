package com.beyazid.perform.data.repository.standings

import androidx.lifecycle.LiveData
import com.beyazid.perform.model.scores.ScoresResponse
import java.util.*

/**
 *  Created by beyazid on 2019-05-22.
 */
interface StandingsRepository {
    suspend fun getStandings() : LiveData<Objects>
}