package com.beyazid.perform.di.module

import android.app.Application
import android.content.Context
import com.beyazid.perform.PerformApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    var mApplication =  PerformApp()

    fun AppModule(application: PerformApp){
        mApplication = application
    }

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun providesContext(): Context {
        return mApplication
    }

//    @Provides
//    @Singleton
//    internal fun provideSharedPreferences(application: PerformApp): SharedPreferences {
//        return application.applicationContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
//    }

}