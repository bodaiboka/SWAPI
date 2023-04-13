package com.rbodai.icellswapi.domain.respository

import com.rbodai.icellswapi.domain.model.Planet

interface SwapiRepo {

    suspend fun getPlanets():Map<Int, Planet>

}