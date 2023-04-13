package com.rbodai.icellswapi.data.api

import com.rbodai.icellswapi.data.dto.PlanetListDTO
import com.rbodai.icellswapi.data.dto.ShipDTO
import com.rbodai.icellswapi.data.dto.ShipListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // https://swapi.dev/api/planets/3
    // https://swapi.dev/api/starships/1

    @GET("planets")
    suspend fun getPlanets(
    ): Response<PlanetListDTO>

    @GET("starships")
    suspend fun getShips(
    ): Response<ShipListDTO>

    @GET("starships/{id}")
    suspend fun getShip(
        @Path("id") id: Int
    ): Response<ShipDTO>

}