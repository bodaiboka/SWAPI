package com.rbodai.icellswapi.presentation.viewstates

import com.rbodai.icellswapi.domain.model.Planet

data class PlanetsStateHolder(
    val isLoading: Boolean = false,
    val data: List<Planet>? = null,
    val error: String = ""
)
