package com.beyazid.perform.base

import android.annotation.SuppressLint
import com.beyazid.perform.utils.Result
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.lang.Exception

/**
 *  Created by beyazid on 2019-05-23.
 */
open class BaseRepository {
    @SuppressLint("TimberArgCount")
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result = safeApiResult(call, errorMessage)
        var data: T? = null
        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error ->
                Timber.e("$errorMessage & Exception - ${result.exception}")
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        val response = call.invoke()
        return if (response.isSuccessful) Result.Success(response.body()!!)
        else Result.Error(Exception(errorMessage))

    }

}