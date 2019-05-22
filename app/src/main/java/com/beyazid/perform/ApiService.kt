package com.beyazid.perform

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

const val LATEST_NEWS_END_POINT = "utilities/interviews/techtest/latestnews.xml/"
const val SCORES_END_POINT = "utilities/interviews/techtest/scores.xml/"
const val STANDINGS_END_POINT = "utilities/interviews/techtest/standings.xml/"
/**
 *  Created by beyazid on 2019-05-20.
 */
interface ApiService {

    @GET(value = LATEST_NEWS_END_POINT)
    fun getLatestNewsAsStringAsync() : Deferred<Response<String>>

    @GET(value = SCORES_END_POINT)
    fun getScoresAsStringAsync() : Deferred<Response<String>>

    @GET(value = STANDINGS_END_POINT)
    fun getStandingsAsStringAsync() : Deferred<Response<String>>

}