package com.example.capstonesportapprevoke.utilityTest

import com.example.capstonesportapprevoke.core.data.source.local.entity.CountryEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.SportEntity
import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity
import com.example.capstonesportapprevoke.core.data.source.remote.response.CountryResponse
import com.example.capstonesportapprevoke.core.data.source.remote.response.SportResponse
import com.example.capstonesportapprevoke.core.data.source.remote.response.TeamResponse
import com.example.capstonesportapprevoke.core.domain.model.Country
import com.example.capstonesportapprevoke.core.domain.model.Sport
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.core.utils.DataMapper.mapCountryEntitiesToDomain
import com.example.capstonesportapprevoke.core.utils.DataMapper.mapDomainToTeamEntity
import com.example.capstonesportapprevoke.core.utils.DataMapper.mapResponsesToCountryEntities
import com.example.capstonesportapprevoke.core.utils.DataMapper.mapResponsesToSportEntities
import com.example.capstonesportapprevoke.core.utils.DataMapper.mapResponsesToTeamEntities
import com.example.capstonesportapprevoke.core.utils.DataMapper.mapSportEntitiesToDomain
import com.example.capstonesportapprevoke.core.utils.DataMapper.mapTeamEntitiesToDomain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataMapperTest {

    private lateinit var teamEntity: TeamEntity

    private lateinit var teamResponse: TeamResponse

    private lateinit var sportResponse: SportResponse

    private lateinit var sportEntity: SportEntity

    private lateinit var countryResponse: CountryResponse

    private lateinit var countryEntity: CountryEntity

    private lateinit var sport: Sport

    private lateinit var team: Team

    private lateinit var country: Country

    @Before
    fun setup() {
        val dummyId = "1"
        val dummyName = "test sport"
        val dummyDescription = "for unit test only"
        val dummyCategory = "football"
        val dummyImage = "some_url_link.com"
        val dummyLocation = "jakarta"
        val dummyCountry = "Indonesia"
        val dummyBoolean = false

        sportEntity = SportEntity(
            sportsId = dummyId,
            name = dummyName,
            description = dummyDescription,
            category = dummyCategory,
            image = dummyImage
        )
        sportResponse = SportResponse(
            dummyId,
            dummyName,
            dummyDescription,
            dummyCategory,
            dummyImage
        )
        sport = Sport(
            dummyId,
            dummyName,
            dummyDescription,
            dummyCategory,
            dummyImage,
        )

        teamResponse = TeamResponse(
            dummyId,
            dummyName,
            dummyDescription,
            dummyCategory,
            dummyCountry,
            dummyImage,
            dummyLocation
        )
        teamEntity = TeamEntity(
            dummyId,
            dummyName,
            dummyDescription,
            dummyCategory,
            dummyCountry,
            dummyImage,
            dummyLocation,
            dummyBoolean,
            dummyBoolean
        )
        team = Team(
            dummyId,
            dummyName,
            dummyDescription,
            dummyCategory,
            dummyCountry,
            dummyImage,
            dummyLocation,
            dummyBoolean,
            dummyBoolean
        )

        countryEntity = CountryEntity(dummyName)
        countryResponse = CountryResponse(dummyName)
        country = Country(dummyName)
    }

    @Test
    fun test_mapResponseToSportEntity_function() {
        val mockResponse = listOf(sportResponse)
        Assert.assertEquals(mapResponsesToSportEntities(mockResponse), listOf(sportEntity))
    }

    @Test
    fun test_mapResponsesToTeamEntities_function() {
        val mockResponse = listOf(teamResponse)
        Assert.assertEquals(mapResponsesToTeamEntities(mockResponse), listOf(teamEntity))
    }

    @Test
    fun test_mapResponsesToCountryEntities_function() {
        val mockResponse = listOf(countryResponse)
        Assert.assertEquals(mapResponsesToCountryEntities(mockResponse), listOf(countryEntity))
    }

    @Test
    fun test_mapSportEntitiesToDomain_function() {
        val mockResponse = listOf(sportEntity)
        Assert.assertEquals(mapSportEntitiesToDomain(mockResponse), listOf(sport))
    }

    @Test
    fun test_mapTeamEntitiesToDomain_function() {
        val mockResponse = listOf(teamEntity)
        Assert.assertEquals(mapTeamEntitiesToDomain(mockResponse), listOf(team))
    }

    @Test
    fun test_mapCountryEntitiesToDomain_function() {
        val mockResponse = listOf(countryEntity)
        Assert.assertEquals(mapCountryEntitiesToDomain(mockResponse), listOf(country))
    }

    @Test
    fun test_mapDomainToTeamEntity_function() {
        Assert.assertEquals(mapDomainToTeamEntity(team), teamEntity)
    }
}