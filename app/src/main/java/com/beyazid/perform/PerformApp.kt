package com.beyazid.perform

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDex
import com.beyazid.perform.di.component.DaggerAppComponent
import com.beyazid.perform.di.module.NetworkModule
import com.beyazid.perform.utils.AppUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-20.
 */
class PerformApp : Application() , HasActivityInjector {

    @Inject
    lateinit var appUtils: AppUtils

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DaggerAppComponent.builder()
            .application(this)
            .networkModule(NetworkModule())
            .build()
            .inject(this)
        appUtils.setupNecessaryPlugins()
        Timber.d("timber")
    }

}