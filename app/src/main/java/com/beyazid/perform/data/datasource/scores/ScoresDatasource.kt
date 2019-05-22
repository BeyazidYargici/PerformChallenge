package com.beyazid.perform.data.datasource.scores

import androidx.lifecycle.LiveData
import com.beyazid.perform.model.scores.ScoresResponse
import retrofit2.Response

/**
 *  Created by beyazid on 2019-05-12.
 */
interface ScoresDatasource {
    val fetchedScores: LiveData<ScoresResponse>
    suspend fun getScores() : LiveData<ScoresResponse>
}