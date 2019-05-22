package com.beyazid.perform.ui.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.beyazid.perform.data.repository.scores.ScoresRepository
import com.beyazid.perform.model.scores.ScoresResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScoresViewModel @Inject constructor(private val scoresRepository: ScoresRepository) : ViewModel() {


    var scoresResponse: LiveData<ScoresResponse>? = null

    fun getScores() = viewModelScope.launch {
        scoresResponse = scoresRepository.getScores()
    }

}
