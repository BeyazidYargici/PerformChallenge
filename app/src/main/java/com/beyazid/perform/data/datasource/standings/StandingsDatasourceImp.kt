package com.beyazid.perform.data.datasource.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.base.BaseRepository
import com.beyazid.perform.data.network.ApiService
import com.beyazid.perform.data.model.standings.Competition
import com.beyazid.perform.data.model.standings.StandingsResponse
import com.beyazid.perform.data.network.ErrorHandler
import com.google.gson.Gson
import timber.log.Timber
import xmlToJson
import javax.inject.Inject

/**
 *  This class fetches standings data from remote or local datasource.
 *  Created by beyazid on 2019-05-22.
 */
class StandingsDatasourceImp @Inject constructor(val apiService: ApiService, val gson: Gson) : BaseRepository(),StandingsDatasource {
    var mFetchedStandings = MutableLiveData<Competition>()
    override var status = MutableLiveData<ErrorHandler>()

    override val fetchedStandings: LiveData<Competition>
        get() = mFetchedStandings

    /**
     *  This method is responsible for fetching standings data from api and preparing data to be used in datasource
     */
    override suspend fun getStandings(): LiveData<Competition> {
        return try {
            val response = apiService.getStandingsAsStringAsync().await()
            status.postValue(responseStatusChecker(response))
            val standings = xmlToJson(gson, response.body()!!, StandingsResponse::class.java)
            mFetchedStandings.postValue(standings.gsmrs?.competition)
            fetchedStandings
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            fetchedStandings
        }
    }

}