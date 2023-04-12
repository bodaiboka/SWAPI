package com.rbodai.icellswapi.data.api

import com.rbodai.icellswapi.domain.model.PlanetList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // https://swapi.dev/api/planets/

    @GET("planets")
    suspend fun getPlanets(
    ): Response<PlanetList>

}