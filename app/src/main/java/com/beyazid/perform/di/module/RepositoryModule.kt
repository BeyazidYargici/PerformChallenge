package com.beyazid.perform.di.module

import com.beyazid.perform.data.datasource.latest_news.LatestNewsDatasource
import com.beyazid.perform.data.datasource.scores.ScoresDatasource
import com.beyazid.perform.data.datasource.standings.StandingsDatasource
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepository
import com.beyazid.perform.data.repository.latest_news.LatestNewsRepositoryImp
import com.beyazid.perform.data.repository.scores.ScoresRepository
import com.beyazid.perform.data.repository.scores.ScoresRepositoryImp
import com.beyazid.perform.data.repository.standings.StandingsRepository
import com.beyazid.perform.data.repository.standings.StandingsRepositoryImp
import dagger.Module
import dagger.Provides

@Module(includes = [DataSourceModule::class])
class RepositoryModule {

    @Provides
    internal fun provideLatestNewsRepository(latestNewsDatasource: LatestNewsDatasource): LatestNewsRepository {
        return LatestNewsRepositoryImp(latestNewsDatasource)
    }
    @Provides
    internal fun provideScoresRepository(scoresDatasource: ScoresDatasource): ScoresRepository {
        return ScoresRepositoryImp(scoresDatasource)
    }

    @Provides
    internal fun provideStandingsRepository(standingsDatasource: StandingsDatasource): StandingsRepository {
        return StandingsRepositoryImp(standingsDatasource)
    }

}