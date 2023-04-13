package com.rbodai.icellswapi.data.repositoryimpl

import com.rbodai.icellswapi.data.api.ApiService
import com.rbodai.icellswapi.data.api.SafeApiRequest
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.model.Ship
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import com.rbodai.icellswapi.mappers.toDomainPlanets
import com.rbodai.icellswapi.mappers.toDomainShip
import com.rbodai.icellswapi.mappers.toDomainShips
import javax.inject.Inject

class SwapiRepoImpl @Inject constructor(private val apiService: ApiService) :
    SwapiRepo, SafeApiRequest() {
    override suspend fun getPlanets(): Map<Int, Planet> {
        val response = safeApiRequest { apiService.getPlanets() }
        return response.results.toDomainPlanets()
    }

    override suspend fun getShips(): List<Ship> {
        val response = safeApiRequest { apiService.getShips() }
        return response.results.toDomainShips()
    }

    override suspend fun getShip(id: Int): Ship {
        val response = safeApiRequest { apiService.getShip(id) }
        return toDomainShip(response)
    }

}