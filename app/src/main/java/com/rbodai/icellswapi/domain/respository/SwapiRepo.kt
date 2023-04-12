package com.rbodai.icellswapi.domain.respository

import com.rbodai.icellswapi.domain.model.Planet

interface SwapiRepo {

    suspend fun getPlanets():List<Planet>

}