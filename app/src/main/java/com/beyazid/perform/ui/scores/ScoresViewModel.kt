package com.beyazid.perform.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.data.repository.scores.ScoresRepository
import com.beyazid.perform.data.model.scores.ScoresResponse
import com.beyazid.perform.data.network.ErrorHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScoresViewModel @Inject constructor(private val scoresRepository: ScoresRepository) : ViewModel() {

    var status: LiveData<ErrorHandler>? = null
    var scoresResponse: LiveData<ScoresResponse>? = null

    /**
     *  Feeds UI with fetched data from repository
     */
    fun getScores() = viewModelScope.launch {
        scoresResponse = scoresRepository.getScores()
        status = scoresRepository.status
    }
}
