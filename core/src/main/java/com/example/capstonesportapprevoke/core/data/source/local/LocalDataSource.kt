package com.example.capstonesportapprevoke.core.data.source.local

import com.example.capstonesportapprevoke.core.data.source.local.entity.CountryEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.SportEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity
import com.example.capstonesportapprevoke.core.data.source.local.room.SportDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val sportDao: SportDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: SportDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }

    fun getAllSport(): Flow<List<SportEntity>> = sportDao.getAllSport()

    fun getAllTeam(): Flow<List<TeamEntity>> = sportDao.getAllTeam()

    fun getTeamFromCountry(countryName: String): Flow<List<TeamEntity>> = sportDao.getTeamFromCountry(countryName)

    fun getAllCountry(): Flow<List<CountryEntity>> = sportDao.getAllCountry()

    fun getFavoriteTeam(): Flow<List<TeamEntity>> = sportDao.getFavoriteTeam()

    fun getSeenTeam(): Flow<List<TeamEntity>> = sportDao.getSeenTeam()

    suspend fun insertSport(sportList: List<SportEntity>) = sportDao.insertSport(sportList)

    suspend fun insertTeam(teamEntity: List<TeamEntity>) = sportDao.insertTeam(teamEntity)

    suspend fun insertCountry(countryList: List<CountryEntity>) =
        sportDao.insertCountry(countryList)

    fun setFavoriteTeam(team: TeamEntity, newState: Boolean) {
        team.isFavorite = newState
        team.isSeen = newState
        sportDao.updateTeam(team)
    }

    fun setSeenTeam(team: TeamEntity, newState: Boolean) {
        team.isSeen = newState
        sportDao.updateTeam(team)
    }

    fun searchTeam(sport: String): Flow<List<TeamEntity>> = sportDao.searchTeam(sport)
}
