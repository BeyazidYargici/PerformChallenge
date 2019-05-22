package com.beyazid.perform.ui.standing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beyazid.perform.data.repository.scores.ScoresRepository
import com.beyazid.perform.data.repository.standings.StandingsRepository
import com.beyazid.perform.ui.scores.ScoresViewModel
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-22.
 */
class StandingsVMFactory  @Inject constructor(private val standingsRepository: StandingsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StandingsViewModel(standingsRepository) as T
    }
}