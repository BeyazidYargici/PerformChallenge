package com.beyazid.perform.di.module

import com.beyazid.perform.base.BaseFragment
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepository
import com.beyazid.perform.ui.latest_news.LatestNewsFragment
import com.beyazid.perform.ui.scores.ScoresFragment
import com.beyazid.perform.ui.standing.StandingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun contributeLatestNews(): LatestNewsFragment

    @ContributesAndroidInjector
    abstract fun contributeScoresFragment(): ScoresFragment

    @ContributesAndroidInjector
    abstract fun contributeStandingsFragment(): StandingsFragment


}