package com.beyazid.perform.utils

import android.content.Context
import com.beyazid.perform.PerformApp
import com.beyazid.perform.BuildConfig
import com.facebook.stetho.Stetho
import io.paperdb.Paper
import timber.log.Timber
import javax.inject.Inject

/**
 *  This class using to start required environments at [PerformApp] started.
 *  Created by beyazid on 2019-05-22.
 */
class AppUtils @Inject constructor(val application: PerformApp, val context: Context, val initializer: Stetho.Initializer) {

    fun setupNecessaryPlugins() {
        Stetho.initialize(initializer)
        Paper.init(application)

        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }

    /**
     * I am using Timber for logging.
     * I customized to improving readability
     * LogCat shows line, method name and class name
     *
     */
    inner class MyDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? {
            return String.format(
                "[Line:%s] [Method:%s] [Class:%s]",
                element.lineNumber,
                element.methodName,
                super.createStackElementTag(element)
            )
        }
    }
}