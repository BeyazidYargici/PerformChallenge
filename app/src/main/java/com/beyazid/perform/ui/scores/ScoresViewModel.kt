package com.beyazid.perform.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.data.repository.scores.ScoresRepository
import com.beyazid.perform.model.scores.ScoresResponse
import com.beyazid.perform.network.ErrorHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

class ScoresViewModel @Inject constructor(private val scoresRepository: ScoresRepository) : ViewModel() {

    var status: LiveData<ErrorHandler>? = null
    var scoresResponse: LiveData<ScoresResponse>? = null

    fun getScores() = viewModelScope.launch {
        scoresResponse = scoresRepository.getScores()
        status = scoresRepository.status
    }
}
