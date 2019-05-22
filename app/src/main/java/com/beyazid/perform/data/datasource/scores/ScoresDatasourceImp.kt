package com.beyazid.perform.data.datasource.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.ApiService
import com.beyazid.perform.model.latests_news.LatestNewsResponse
import com.beyazid.perform.model.scores.ScoresResponse
import com.google.gson.Gson
import retrofit2.Response
import timber.log.Timber
import xmlToJson
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class ScoresDatasourceImp @Inject constructor(private val apiService: ApiService,private val gson: Gson) : ScoresDatasource {

    var mFetchedDummy = MutableLiveData<ScoresResponse>()

    override val fetchedDummy: LiveData<ScoresResponse>
        get() = mFetchedDummy

    override suspend fun getDummy(): LiveData<ScoresResponse> {
        return try {
            val xmlResponse= apiService.getScoresAsStringAsync().await()
            val scoresResponse= xmlToJson(gson, xmlResponse.body()!!, ScoresResponse::class.java)
            mFetchedDummy.postValue(scoresResponse)
            fetchedDummy
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            fetchedDummy
        }
    }
}