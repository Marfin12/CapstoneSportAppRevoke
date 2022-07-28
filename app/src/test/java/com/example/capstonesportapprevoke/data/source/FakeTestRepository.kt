package com.example.capstonesportapprevoke.data.source

import android.util.Log
import com.example.capstonesportapprevoke.core.data.Resource
import com.example.capstonesportapprevoke.core.domain.model.Country
import com.example.capstonesportapprevoke.core.domain.model.Sport
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.core.domain.repository.ISportRepository
import com.example.capstonesportapprevoke.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class FakeTestRepository : ISportRepository {

    private val apiService = FakeApiService()

    override fun getAllSport(): Flow<Resource<List<Sport>>> {
        return flow {
            try {
                val response = apiService.getSportList()
                val dataArray = response.sports
                if (dataArray.isNotEmpty()){
                    emit(Resource.Success(dataArray))
                } else {
                    emit(Resource.Loading(dataArray))
                }
            } catch (e : Exception){
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.Main)
    }

    override fun getAllTeam(): Flow<Resource<List<Team>>> {
        return flow {
            try {
                val response = apiService.getTeamList()
                if (response.isNotEmpty()) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Loading(response))
                }
            } catch (e : Exception){
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.Main)
    }

    override fun getTeamFromCountry(countryName: String): Flow<Resource<List<Team>>> {
        return flow {
            try {
                val response = apiService.getTeamList()
                if (response.isNotEmpty()) {
                    val dataFilter = response.filter {
                        it.country == countryName
                    }
                    if (dataFilter.isNotEmpty()) {
                        emit(Resource.Success(response))
                    }
                    else {
                        emit(Resource.Loading(response))
                    }
                } else {
                    emit(Resource.Loading(response))
                }
            } catch (e : Exception){
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.Main)
    }

    override fun getAllCountry(): Flow<Resource<List<Country>>> {
        return flow {
            try {
                val response = apiService.getCountryList()
                if (response.isNotEmpty()) {
                    emit(Resource.Success(response))
                } else {
                    emit(Resource.Loading(response))
                }
            } catch (e : Exception){
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.Main)
    }

    override fun getFavoriteTeam(): Flow<List<Team>> {
        return flow {
            try {
                val response = apiService.getTeamList()
                if (response.isNotEmpty()) {
                    val listFavoriteTeam = response.filter {
                        it.isFavorite
                    }
                    emit(listFavoriteTeam)
                } else {
                    emit(response)
                }
            } catch (e : Exception){
                emit(listOf())
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    override fun getSeenTeam(): Flow<List<Team>> {
        return flow {
            try {
                val response = apiService.getTeamList()
                if (response.isNotEmpty()) {
                    val listFavoriteTeam = response.filter {
                        it.isSeen
                    }
                    emit(listFavoriteTeam)
                } else {
                    emit(response)
                }
            } catch (e : Exception){
                emit(listOf())
                Log.e("RemoteDataSource", e.toString())
            }
        }
    }

    override fun setFavoriteTeam(team: Team, state: Boolean) {
        val teamEntity = DataMapper.mapDomainToTeamEntity(team)
        teamEntity.isFavorite = state
    }

    override fun setSeenTeam(team: Team, state: Boolean) {
        val teamEntity = DataMapper.mapDomainToTeamEntity(team)
        teamEntity.isFavorite = state
    }

    override fun searchTeam(
        teamName: String,
        localDataTeam: ArrayList<String>
    ): Flow<Resource<List<Team>>> {
        return flow {
            try {
                val response = apiService.searchTeam(teamName)
                val teamResult = response.filter { !localDataTeam.contains(it.name) }
                if (teamResult.isNotEmpty()) {
                    emit(Resource.Success(teamResult))
                } else {
                    emit(Resource.Loading(teamResult))
                }
            } catch (e : Exception){
                emit(Resource.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.Main)
    }

    override fun getHttpClient(): OkHttpClient {
        val hostname = "thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/Yl6IYMBnxrwtn9DWo32vSutvY3gRn56iEfWj5/66c88=")
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

}