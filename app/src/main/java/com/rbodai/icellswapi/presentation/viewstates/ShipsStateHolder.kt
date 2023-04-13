package com.rbodai.icellswapi.presentation.viewstates

import com.rbodai.icellswapi.domain.model.Ship

data class ShipsStateHolder(
    val isLoading: Boolean = false,
    val data: List<Ship>? = null,
    val error: String = ""
)
