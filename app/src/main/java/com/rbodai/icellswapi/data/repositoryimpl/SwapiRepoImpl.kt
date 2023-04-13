package com.rbodai.icellswapi.data.repositoryimpl

import com.rbodai.icellswapi.data.api.ApiService
import com.rbodai.icellswapi.data.api.SafeApiRequest
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import com.rbodai.icellswapi.mappers.toDomainPlanet
import javax.inject.Inject

val fakePlanets: List<Planet> = listOf(
//    Planet("Tatooine", "desert", "url", "200000, 152000, 1),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 2),
//    Planet("Earth", "desert", "url", 200000, 152000, 3),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 4),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 5),
//    Planet("Hoth", "desert", "url", 200000, 152000, 6),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 7),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 8),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 9),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 10),
//    Planet("Tatooine", "desert", "url", 200000, 152000, 11),
//    Planet("Mars", "desert", "url", 200000, 152000, 12),
)
class SwapiRepoImpl @Inject constructor(private val apiService: ApiService) :
    SwapiRepo, SafeApiRequest() {
    override suspend fun getPlanets(): Map<Int, Planet> {
        val response = safeApiRequest { apiService.getPlanets() }
        return response?.results?.toDomainPlanet()!!
    }

//    override suspend fun getPlanets(): List<Planet> {
//        return fakePlanets
//    }

}