package com.beyazid.perform.utils
//
///**
// *  Created by beyazid on 2019-05-19.
// */

class LiveDataResults<T>(val status: STATUS, val data: T? = null, val error: Throwable? = null) {

    enum class STATUS {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> success (data: T) = LiveDataResults<T>(STATUS.SUCCESS, data)
        fun <T> error(err: Throwable) = LiveDataResults<T>(STATUS.ERROR, null, err)
        fun <T> loading() = LiveDataResults<T>(STATUS.LOADING)
    }
}
