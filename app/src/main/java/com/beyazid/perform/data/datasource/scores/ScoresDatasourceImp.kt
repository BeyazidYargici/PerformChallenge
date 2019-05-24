package com.beyazid.perform.data.datasource.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.base.BaseRepository
import com.beyazid.perform.model.latests_news.LatestNewsResponse
import com.beyazid.perform.network.ApiService
import com.beyazid.perform.model.scores.ScoresResponse
import com.beyazid.perform.network.ErrorHandler
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import xmlToJson
import java.util.*
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class ScoresDatasourceImp @Inject constructor(private val apiService: ApiService, private val gson: Gson) : BaseRepository(),ScoresDatasource {

    var mFetchedScores = MutableLiveData<ScoresResponse>()
    override var status = MutableLiveData<ErrorHandler>()

    override val fetchedScores: LiveData<ScoresResponse>
        get() = mFetchedScores

    override suspend fun getScores(): LiveData<ScoresResponse> {
        return try {
            val response= apiService.getScoresAsStringAsync().await()
            status.postValue(responseStatusChecker(response))
            val scoresResponse= xmlToJson(gson, response.body()!!, ScoresResponse::class.java)
            mFetchedScores.postValue(scoresResponse)
            fetchedScores
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            fetchedScores
        }
    }


}