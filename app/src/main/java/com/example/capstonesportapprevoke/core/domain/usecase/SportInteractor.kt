package com.example.capstonesportapprevoke.core.domain.usecase

import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.core.domain.repository.ISportRepository
import okhttp3.OkHttpClient
import javax.inject.Inject

class SportInteractor @Inject constructor(private val sportRepository: ISportRepository): SportUseCase {
    override fun getAllSport() = sportRepository.getAllSport()

    override fun getAllTeam() = sportRepository.getAllTeam()

    override fun getTeamFromCountry(countryName: String) = sportRepository.getTeamFromCountry(countryName)

    override fun getAllCountry() = sportRepository.getAllCountry()


    override fun getFavoriteTeam() = sportRepository.getFavoriteTeam()

    override fun getSeenTeam() =  sportRepository.getSeenTeam()


    override fun setFavoriteTeam(team: Team, state: Boolean) = sportRepository.setFavoriteTeam(team, state)

    override fun setSeenTeam(team: Team, state: Boolean) = sportRepository.setSeenTeam(team, state)

    override fun searchTeam(teamName: String, localDataTeam: ArrayList<String>) =
        sportRepository.searchTeam(teamName, localDataTeam)


    override fun getHttpClient(): OkHttpClient = sportRepository.getHttpClient()
}