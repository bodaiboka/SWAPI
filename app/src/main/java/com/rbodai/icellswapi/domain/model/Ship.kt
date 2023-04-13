package com.rbodai.icellswapi.domain.model

data class Ship (
    val name: String,
    val model: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val max_atmosphering_speed: String,
    val crew: String,
    val starship_class: String,
    val url: String,
    val id: Int
)