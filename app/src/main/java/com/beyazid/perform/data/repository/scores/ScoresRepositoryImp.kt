package com.beyazid.perform.data.repository.scores

import androidx.lifecycle.LiveData
import com.beyazid.perform.data.datasource.scores.ScoresDatasource
import com.beyazid.perform.model.scores.ScoresResponse
import com.beyazid.perform.network.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class ScoresRepositoryImp @Inject constructor(private val scoresDatasource: ScoresDatasource) :
    ScoresRepository {
    override var status: LiveData<ErrorHandler>
        get() = scoresDatasource.status
        set(value) {}

    override suspend fun getScores(): LiveData<ScoresResponse> {
        return withContext(Dispatchers.IO) {
            scoresDatasource.getScores()
        }
    }
}