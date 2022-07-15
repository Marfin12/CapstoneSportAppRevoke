package com.example.capstonesportapprevoke.detail

import androidx.lifecycle.ViewModel
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val sportUseCase: SportUseCase) : ViewModel() {
    fun setFavoriteSport(team: Team, newStatus:Boolean) =
        sportUseCase.setFavoriteTeam(team, newStatus)

    fun setSeenTeam(team: Team) =
        sportUseCase.setSeenTeam(team, true)
}