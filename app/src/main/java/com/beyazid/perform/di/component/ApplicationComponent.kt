package com.beyazid.perform.di.component

import com.beyazid.perform.PerformApp
import com.beyazid.perform.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// I separated the modules and gave to AppComponent
@Singleton
@Component(
    modules = [
        AppModule::class,
        FragmentModule::class,
        ActivityModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PerformApp): Builder

        @BindsInstance
        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(application: PerformApp)

}