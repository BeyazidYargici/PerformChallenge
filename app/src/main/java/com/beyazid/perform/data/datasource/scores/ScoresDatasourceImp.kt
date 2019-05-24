package com.beyazid.perform.data.datasource.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.network.ApiService
import com.beyazid.perform.model.scores.ScoresResponse
import com.google.gson.Gson
import timber.log.Timber
import xmlToJson
import java.util.*
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class ScoresDatasourceImp @Inject constructor(private val apiService: ApiService, private val gson: Gson) : ScoresDatasource {

    var mFetchedScores = MutableLiveData<ScoresResponse>()

    override val fetchedScores: LiveData<ScoresResponse>
        get() = mFetchedScores

    override suspend fun getScores(): LiveData<ScoresResponse> {
        return try {
            val xmlResponse= apiService.getScoresAsStringAsync().await()
            val scoresResponse= xmlToJson(gson, xmlResponse.body()!!, ScoresResponse::class.java)
            mFetchedScores.postValue(scoresResponse)
            fetchedScores
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            fetchedScores
        }
    }

//    lateinit var timer : Timer

//    private fun startTimer() {
//        timer = Timer()
//        timer.scheduleAtFixedRate(TimerTask {
//            public void run() {
//                new AsyncDataClass2  adc = new AsyncDataClass2("u","p");
//                adc.execute();
//            }
//        }, 0, 1000);
//    }

}