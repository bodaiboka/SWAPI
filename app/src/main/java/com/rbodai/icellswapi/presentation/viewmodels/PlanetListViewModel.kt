package com.rbodai.icellswapi.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbodai.icellswapi.data.api.Resource
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.use_cases.GetPlanetsUseCase
import com.rbodai.icellswapi.presentation.viewstates.ListViewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(private val getPlanetsUseCase: GetPlanetsUseCase) :
    ViewModel() {

    val planets = mutableStateOf(ListViewStateHolder<Map<Int, Planet>>())

    init {
        getPlanets()
    }

    fun getPlanets() {
        getPlanetsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    planets.value = ListViewStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    planets.value = ListViewStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    planets.value = ListViewStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}