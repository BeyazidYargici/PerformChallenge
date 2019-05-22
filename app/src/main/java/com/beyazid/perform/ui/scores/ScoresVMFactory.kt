package com.beyazid.perform.ui.scores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beyazid.perform.data.repository.scores.ScoresRepository
import javax.inject.Inject

/**
 *  Created by beyazid on 2019-05-12.
 */
class ScoresVMFactory @Inject constructor(private val scoresRepository: ScoresRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScoresViewModel(scoresRepository) as T
    }
}