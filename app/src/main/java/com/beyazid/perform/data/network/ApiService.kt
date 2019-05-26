package com.beyazid.perform.data.network

import com.beyazid.perform.utils.LATEST_NEWS_END_POINT
import com.beyazid.perform.utils.SCORES_END_POINT
import com.beyazid.perform.utils.STANDINGS_END_POINT
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 *  Created by beyazid on 2019-05-20.
 *  This interface contains network methods. This will be using by Retrofit.
 *  Retrofit generates network connections
 *  I am using [Deferred.class] to use with Coroutines
 */
interface ApiService {

    @GET(value = LATEST_NEWS_END_POINT)
    fun getLatestNewsAsStringAsync() : Deferred<Response<String>>

    @GET(value = SCORES_END_POINT)
    fun getScoresAsStringAsync() : Deferred<Response<String>>

    @GET(value = STANDINGS_END_POINT)
    fun getStandingsAsStringAsync() : Deferred<Response<String>>

}