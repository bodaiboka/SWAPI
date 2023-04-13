package com.rbodai.icellswapi.domain.model

data class Planet (
    val name: String,
    val url: String,
    val terrain: String,
    val population: String,
    val diameter: String,
    val orbital_period: String,
    val id: Int,
    val climate: String,
    val rotation_period: String
)