package com.beyazid.perform.data.repository.scores

import androidx.lifecycle.LiveData
import com.beyazid.perform.data.datasource.scores.ScoresDatasource
import com.beyazid.perform.model.scores.ScoresResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class ScoresRepositoryImp @Inject constructor(private val scoresDatasource: ScoresDatasource) :
    ScoresRepository {
    override suspend fun getDummy(): LiveData<ScoresResponse> {
        return withContext(Dispatchers.IO) {
            scoresDatasource.getDummy()
        }
    }
}