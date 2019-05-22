package com.beyazid.perform.data.repository.scores

import androidx.lifecycle.LiveData
import com.beyazid.perform.model.scores.ScoresResponse
import retrofit2.Response

/**
 *  Created by beyazid on 2019-05-12.
 */
interface ScoresRepository {
    suspend fun getDummy() : LiveData<ScoresResponse>
}