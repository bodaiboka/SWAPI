package com.rbodai.icellswapi.mappers

import com.rbodai.icellswapi.data.dto.PlanetDTO
import com.rbodai.icellswapi.domain.model.Planet


fun List<PlanetDTO>.toDomainPlanet():Map<Int, Planet> {

    return map {
        Planet(
            name = it.name ?: "",
            url = it.url ?: "",
            terrain = it.terrain ?: "",
            population = it.population ?:"",
            diameter = it.diameter ?:"",
            id = it.url?.split("/")?.get(5)?.toInt()!!
        )
    }.associateBy { it.id }
}


