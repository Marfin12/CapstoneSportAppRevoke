package com.example.capstonesportapprevoke.core.data.source.remote

import android.util.Log
import com.example.capstonesportapprevoke.core.data.source.remote.network.ApiResponse
import com.example.capstonesportapprevoke.core.data.source.remote.network.ApiService
import com.example.capstonesportapprevoke.core.data.source.remote.response.CountryResponse
import com.example.capstonesportapprevoke.core.data.source.remote.response.SportResponse
import com.example.capstonesportapprevoke.core.data.source.remote.response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllSport(): Flow<ApiResponse<List<SportResponse>>> {
        return flow {
            try {
                val response = apiService.getSportList()
                val dataArray = response.sports
                val dataFilter = dataArray.filter { it.category == "TeamvsTeam"}
                if (dataFilter.isNotEmpty()){
                    emit(ApiResponse.Success(dataFilter))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllCountry(): Flow<ApiResponse<List<CountryResponse>>> {
        return flow {
            try {
                val response = apiService.getCountryList()
                val dataArray = response.countries
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTeamFromCountry(countryName: String): Flow<ApiResponse<List<TeamResponse>>> { //di panggil ketika resource.success di homeviewmode
        return flow {
            try {
                val sportResponse = apiService.getSportList()
                val sportArray = sportResponse.sports
                val sportFilter = sportArray.filter { it.category == "TeamvsTeam"}
                if (sportFilter.isNotEmpty()) {
                    val teamList = ArrayList<TeamResponse>()
                    sportFilter.map { sport ->
                        val teamResponse = apiService.getTeamList(countryName, sport.name)
                        if (teamResponse.team != null) {
                            teamResponse.team.filter { it.description == null }.map {
                                it.description = "Description is not available yet!"
                            }
                            teamList.addAll(teamResponse.team)
                        }
                    }
                    if (teamList.isNotEmpty()) {
                        emit(ApiResponse.Success(teamList))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchTeam(teamSearch: String, localTeamName: ArrayList<String>): Flow<ApiResponse<List<TeamResponse>>> {
        return flow {
            try {
                val responses = apiService.searchTeam(teamSearch)
                val dataArray = responses.team
                val teamList = ArrayList<TeamResponse>()
                if (dataArray != null) {
                    dataArray.filter { it.description == null }.map {
                        it.description = "Description is not available yet!"
                    }
                    teamList.addAll(dataArray)
                }
                val teamResult = teamList.filter { !localTeamName.contains(it.name) }
                if (teamResult.isNotEmpty()) {
                    emit(ApiResponse.Success(teamResult))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
