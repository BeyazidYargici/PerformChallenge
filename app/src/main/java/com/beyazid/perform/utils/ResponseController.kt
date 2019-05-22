//package com.beyazid.perform.utils
//
//import android.content.Context
//import retrofit2.Response
//import timber.log.Timber
//
///**
// *  Handling error codes, null body etc. Create a dialog if response failed
// *  Created by beyazid on 2019-05-19.
// */
//class ResponseController<T>(val data: Response<T>?, context: Context){
//    init {
//        if (data?.isSuccessful!!) {
//            if (data.code() == 200) {
//                if (data.body() != null) {
//                    Timber.e(data.code().toString())
//                } else {
//                    createDialog(context, data.code().toString(), data.message())
//                }
//            } else {
//                createDialog(context, data.code().toString(), data.message())
//            }
//        }else{
//            createDialog(context, data.code().toString(), data.message())
//        }
//    }
//}