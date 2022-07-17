package com.example.capstonesportapprevoke.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListCountryResponse(
    @field:SerializedName("countries")
    val countries: List<CountryResponse>
)
