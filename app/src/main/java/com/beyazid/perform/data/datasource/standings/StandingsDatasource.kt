package com.beyazid.perform.data.datasource.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.data.model.standings.Competition
import com.beyazid.perform.data.network.ErrorHandler

/**
 *  Created by beyazid on 2019-05-22.
 */
interface StandingsDatasource {
    val fetchedStandings: LiveData<Competition>
    suspend fun getStandings() : LiveData<Competition>
    var status: MutableLiveData<ErrorHandler>
}