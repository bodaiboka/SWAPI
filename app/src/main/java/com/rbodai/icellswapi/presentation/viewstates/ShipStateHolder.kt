package com.rbodai.icellswapi.presentation.viewstates

import com.rbodai.icellswapi.domain.model.Ship

data class ShipStateHolder(
    val isLoading: Boolean = false,
    val data: Ship? = null,
    val error: String = ""
)
