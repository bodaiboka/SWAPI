package com.rbodai.icellswapi.domain.respository

import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.model.Ship

interface SwapiRepo {

    suspend fun getPlanets():Map<Int, Planet>

    suspend fun getShips():List<Ship>

    suspend fun getShip(id: Int):Ship

}