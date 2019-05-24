package com.beyazid.perform.base

import androidx.lifecycle.MutableLiveData
import com.beyazid.perform.network.ErrorHandler
import com.beyazid.perform.network.Status
import retrofit2.Response

/**
 *  Created by beyazid on 2019-05-23.
 */
open class BaseRepository {

    fun responseStatusChecker(response: Response<String>) : ErrorHandler {
        return if (response.isSuccessful) if (response.code() == 200) {
            when {
                response.body() != null && response.body()!!.contains("<?xml") -> ErrorHandler(Status.SUCCESS, response.code(), response.body())
                else -> ErrorHandler(Status.UNKNOWN, response.code(), response.message())
            }
        } else ErrorHandler(Status.ERROR, response.code(), "${response.code()} ${response.errorBody().toString()}")
        else ErrorHandler(Status.FAILURE, response.code(), response.errorBody().toString())
    }

}