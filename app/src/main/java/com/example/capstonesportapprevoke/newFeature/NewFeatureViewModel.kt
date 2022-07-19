package com.example.capstonesportapprevoke.newFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase

class NewFeatureViewModel(private val sportUseCase: SportUseCase) : ViewModel() {
    val seenTeam = sportUseCase.getSeenTeam().asLiveData()
}
