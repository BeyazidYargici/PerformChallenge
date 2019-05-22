package com.beyazid.perform.di.module

import androidx.lifecycle.ViewModelProvider
import com.beyazid.perform.ui.latest_news.LatestNewsVMFactory
import com.beyazid.perform.ui.scores.ScoresVMFactory
import com.beyazid.perform.ui.standing.StandingsVMFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindLatestNewsViewModelFactory(latestNewsVMFactory: LatestNewsVMFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindScoresViewModelFactory(scoresVMFactory: ScoresVMFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindStandingsViewModelFactory(standingsVMFactory: StandingsVMFactory): ViewModelProvider.Factory

}
