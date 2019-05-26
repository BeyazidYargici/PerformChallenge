package com.beyazid.perform.data.datasource.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.base.BaseRepository
import com.beyazid.perform.data.network.ApiService
import com.beyazid.perform.data.model.scores.ScoresResponse
import com.beyazid.perform.data.network.ErrorHandler
import com.google.gson.Gson
import timber.log.Timber
import xmlToJson
import javax.inject.Inject

/**
 * This class fetches scores from remote or local datasource.
 *  Created by beyazid on 2019-05-22.
 */
class ScoresDatasourceImp @Inject constructor(private val apiService: ApiService, private val gson: Gson) : BaseRepository(),ScoresDatasource {

    var mFetchedScores = MutableLiveData<ScoresResponse>()
    override var status = MutableLiveData<ErrorHandler>()

    override val fetchedScores: LiveData<ScoresResponse>
        get() = mFetchedScores

    /**
     *  This method is responsible for fetching scores from api and preparing data to be used in datasource
     */
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