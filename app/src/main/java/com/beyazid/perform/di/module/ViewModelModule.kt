package com.beyazid.perform.di.module

import androidx.lifecycle.ViewModel
import com.beyazid.perform.di.ViewModelKey
import com.beyazid.perform.ui.latest_news.LatestNewsViewModel
import com.beyazid.perform.ui.scores.ScoresViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    /*
     * This method basically says inject this object into a Map using the @IntoMap annotation,
     * with the  YourViewModel.class as key,
     * and a Provider that will build a YourViewModel object.
     *
     */
    @Binds
    @IntoMap
    @ViewModelKey(LatestNewsViewModel::class)
    protected abstract fun bindLatestNewsViewModel(latestNewsViewModel: LatestNewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScoresViewModel::class)
    protected abstract fun bindScoresViewModel(scoresViewModel: ScoresViewModel): ViewModel

}
