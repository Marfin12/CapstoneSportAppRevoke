package com.example.capstonesportapprevoke.data.source

import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity
import com.example.capstonesportapprevoke.core.domain.model.Country
import com.example.capstonesportapprevoke.core.domain.model.Sport
import com.example.capstonesportapprevoke.core.domain.model.Team

class FakeApiService {

    companion object {
        val dummyId = "1"
        val dummyName = "test sport"
        val dummyDescription = "for unit test only"
        val dummyCategory = "football"
        val dummyImage = "some_url_link.com"
        val dummyLocation = "jakarta"
        val dummyCountry = "Indonesia"
        val dummyBoolean = false
    }

    fun getSportList(): FakeListSportResponse {
        return FakeListSportResponse(
            listOf(
                Sport(
                    sportsId = dummyId,
                    name = dummyName,
                    description = dummyDescription,
                    category = dummyCategory,
                    image = dummyImage
                )
            )
        )
    }

    fun getTeamList(): List<Team> {
        return listOf(
            Team(
                dummyId,
                dummyName,
                dummyDescription,
                dummyCategory,
                dummyCountry,
                dummyImage,
                dummyLocation,
                dummyBoolean,
                dummyBoolean
            ),
            Team(
                dummyId,
                "sport ok",
                dummyDescription,
                dummyCategory,
                "testCountry",
                dummyImage,
                dummyLocation,
                dummyBoolean,
                dummyBoolean
            ),
            Team(
                dummyId,
                "other sport",
                dummyDescription,
                dummyCategory,
                "testCountry",
                dummyImage,
                dummyLocation,
                dummyBoolean,
                dummyBoolean
            )
        )
    }

    fun getCountryList(): List<Country> {
        return listOf(
            Country(
                name_en = dummyName
            )
        )
    }

    fun getTeamEntityList(): List<TeamEntity> {
        return listOf(
            TeamEntity(
                dummyId,
                dummyName,
                dummyDescription,
                dummyCategory,
                dummyCountry,
                dummyImage,
                dummyLocation,
                dummyBoolean,
                dummyBoolean
            ),
            TeamEntity(
                dummyId,
                "sport ok",
                dummyDescription,
                dummyCategory,
                "testCountry",
                dummyImage,
                dummyLocation,
                isFavorite = true,
                isSeen = true
            ),
            TeamEntity(
                dummyId,
                "sport other",
                dummyDescription,
                dummyCategory,
                "testCountry",
                dummyImage,
                dummyLocation,
                dummyBoolean,
                true
            )
        )
    }

    fun searchTeam(teamName: String): List<Team> = getTeamList().filter {
        it.name == teamName
    }
}