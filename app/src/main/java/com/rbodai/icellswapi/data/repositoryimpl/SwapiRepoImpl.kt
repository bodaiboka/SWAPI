package com.rbodai.icellswapi.data.repositoryimpl

import com.rbodai.icellswapi.data.api.ApiService
import com.rbodai.icellswapi.data.api.SafeApiRequest
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import javax.inject.Inject


val fakePlanets: List<Planet> = listOf(
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Earth", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Hoth", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Tatooine", "desert", "url", 200000, 152000),
    Planet("Mars", "desert", "url", 200000, 152000),
)
class SwapiRepoImpl @Inject constructor(private val apiService: ApiService) :
    SwapiRepo, SafeApiRequest() {
//    override suspend fun getPlanets(): List<Planet> {
//        val response = safeApiRequest { apiService.getPlanets() }
//        return response.results
//    }

    override suspend fun getPlanets(): List<Planet> {
        return fakePlanets
    }

}