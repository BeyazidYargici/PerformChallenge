package com.beyazid.perform.data.repository.scores

import androidx.lifecycle.LiveData
import com.beyazid.perform.model.scores.ScoresResponse
import com.beyazid.perform.network.ErrorHandler
import retrofit2.Response

/**
 *  Created by beyazid on 2019-05-12.
 */
interface ScoresRepository {
    suspend fun getScores() : LiveData<ScoresResponse>
    var status: LiveData<ErrorHandler>
}