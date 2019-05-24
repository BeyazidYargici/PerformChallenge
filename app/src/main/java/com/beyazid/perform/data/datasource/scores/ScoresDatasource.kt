package com.beyazid.perform.data.datasource.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.model.scores.ScoresResponse
import com.beyazid.perform.network.ErrorHandler
import retrofit2.Response

/**
 *  Created by beyazid on 2019-05-12.
 */
interface ScoresDatasource {
    val fetchedScores: LiveData<ScoresResponse>
    suspend fun getScores() : LiveData<ScoresResponse>
    var status: MutableLiveData<ErrorHandler>
}