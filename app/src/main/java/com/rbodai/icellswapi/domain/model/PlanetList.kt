package com.rbodai.icellswapi.domain.model

import com.rbodai.icellswapi.data.dto.PlanetDTO

data class PlanetList (
    val results: List<PlanetDTO>,
    val count: Int
)