package com.rbodai.icellswapi.data.repositoryimpl

import com.rbodai.icellswapi.data.api.ApiService
import com.rbodai.icellswapi.data.api.SafeApiRequest
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import javax.inject.Inject

class SwapiRepoImpl @Inject constructor(private val apiService: ApiService) :
    SwapiRepo, SafeApiRequest() {
    override suspend fun getPlanets(): List<Planet> {
        val response = safeApiRequest { apiService.getPlanets() }
        return response.results
    }

}