package com.beyazid.perform.di.module

import com.beyazid.perform.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

//    @ContributesAndroidInjector(modules = [FragmentModule::class])
//    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

}