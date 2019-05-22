package com.beyazid.perform.di.module

import com.beyazid.perform.ApiService
import com.beyazid.perform.data.datasource.latest_news.LatestNewsDatasource
import com.beyazid.perform.data.datasource.latest_news.LatestNewsDatasourceImp
import com.beyazid.perform.data.datasource.scores.ScoresDatasource
import com.beyazid.perform.data.datasource.scores.ScoresDatasourceImp
import com.beyazid.perform.data.datasource.standings.StandingsDatasource
import com.beyazid.perform.data.datasource.standings.StandingsDatasourceImp
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    internal fun provideLatestNewsDatasource(apiService: ApiService, gson: Gson): LatestNewsDatasource {
        return LatestNewsDatasourceImp(apiService,gson)
    }
    @Provides
    internal fun provideScoresDatasource(apiService: ApiService,gson: Gson): ScoresDatasource {
        return ScoresDatasourceImp(apiService,gson)
    }

    @Provides
    internal fun provideStandingsDatasource(apiService: ApiService,gson: Gson): StandingsDatasource {
        return StandingsDatasourceImp(apiService,gson)
    }

}