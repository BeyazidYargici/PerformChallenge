package com.beyazid.perform.data.datasource.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.data.model.scores.ScoresResponse
import com.beyazid.perform.data.network.ErrorHandler

/**
 *  Created by beyazid on 2019-05-22.
 */
interface ScoresDatasource {
    val fetchedScores: LiveData<ScoresResponse>
    suspend fun getScores() : LiveData<ScoresResponse>
    var status: MutableLiveData<ErrorHandler>
}