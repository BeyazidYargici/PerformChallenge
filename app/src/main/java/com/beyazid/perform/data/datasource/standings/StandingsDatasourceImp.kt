package com.beyazid.perform.data.datasource.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.ApiService
import com.beyazid.perform.model.latests_news.LatestNewsItem
import com.beyazid.perform.model.latests_news.LatestNewsResponse
import com.beyazid.perform.model.standings.Competition
import com.beyazid.perform.model.standings.StandingsResponse
import com.google.gson.Gson
import timber.log.Timber
import xmlToJson
import java.util.*
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */
class StandingsDatasourceImp @Inject constructor(val apiService: ApiService, val gson: Gson) : StandingsDatasource {

    var mFetchedStandings = MutableLiveData<Competition>()

    override val fetchedStandings: LiveData<Competition>
        get() = mFetchedStandings

    override suspend fun getStandings(): LiveData<Competition> {
        return try {
            val xmlResponse1 = apiService.getStandingsAsStringAsync().await()
            val standings = xmlToJson(gson, xmlResponse1.body()!!, StandingsResponse::class.java)
            mFetchedStandings.postValue(standings.gsmrs?.competition)
            fetchedStandings
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            fetchedStandings
        }
    }

}