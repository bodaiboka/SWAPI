package com.rbodai.icellswapi.mappers

import com.rbodai.icellswapi.data.dto.PlanetDTO
import com.rbodai.icellswapi.data.dto.ShipDTO
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.model.Ship


fun List<PlanetDTO>.toDomainPlanets():Map<Int, Planet> {

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

fun List<ShipDTO>.toDomainShips():List<Ship> {

    return map {
        Ship(
            name = it.name,
            url = it.url,
            manufacturer = it.manufacturer,
            cost_in_credits = it.cost_in_credits,
            length = it.length,
            max_atmosphering_speed = it.max_atmosphering_speed,
            crew = it.crew,
            starship_class = it.starship_class,
            model = it.model,
            id = it.url.split("/").get(5).toInt()
        )
    }
}

fun toDomainShip(shipDTO: ShipDTO): Ship {
    return Ship(
        name = shipDTO.name,
        url = shipDTO.url,
        manufacturer = shipDTO.manufacturer,
        cost_in_credits = shipDTO.cost_in_credits,
        length = shipDTO.length,
        max_atmosphering_speed = shipDTO.max_atmosphering_speed,
        crew = shipDTO.crew,
        starship_class = shipDTO.starship_class,
        model = shipDTO.model,
        id = shipDTO.url.split("/").get(5).toInt()
    )
}



