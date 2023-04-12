package com.rbodai.icellswapi.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbodai.icellswapi.data.api.Resource
import com.rbodai.icellswapi.domain.use_cases.GetPlanetsUseCase
import com.rbodai.icellswapi.presentation.viewstates.PlanetsStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(private val getPlanetsUseCase: GetPlanetsUseCase) :
    ViewModel() {

    val planets = mutableStateOf(PlanetsStateHolder())

    init {
        getPlanets()
    }

    fun getPlanets() {
        getPlanetsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    planets.value = PlanetsStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    planets.value = PlanetsStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    planets.value = PlanetsStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}