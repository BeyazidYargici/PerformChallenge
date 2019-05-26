package com.beyazid.perform.data.network

/**
 *  Created by beyazid on 2019-05-23.
 */
class ErrorHandler(var status: Status, var code: Int, var message: String?) {
    companion object {
        fun handler(status: Status, code: Int, message: String?): ErrorHandler {
            return ErrorHandler(status, code, message)
        }
    }
}