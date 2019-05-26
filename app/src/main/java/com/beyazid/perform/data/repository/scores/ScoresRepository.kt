package com.beyazid.perform.data.repository.scores

import androidx.lifecycle.LiveData
import com.beyazid.perform.data.model.scores.ScoresResponse
import com.beyazid.perform.data.network.ErrorHandler

/**
 *  Created by beyazid on 2019-05-22.
 */
interface ScoresRepository {
    suspend fun getScores() : LiveData<ScoresResponse>
    var status: LiveData<ErrorHandler>
}