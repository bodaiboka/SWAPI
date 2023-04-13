package com.rbodai.icellswapi.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbodai.icellswapi.data.api.Resource
import com.rbodai.icellswapi.domain.use_cases.GetShipUseCase
import com.rbodai.icellswapi.presentation.viewstates.ShipStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShipViewModel @Inject constructor(private val getShipUseCase: GetShipUseCase, savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private val argument = checkNotNull(savedStateHandle.get<Int>("id"))
    val ship = mutableStateOf(ShipStateHolder())

    init {
        getShips()
    }

    fun getShips() {
        getShipUseCase(argument).onEach {
            when (it) {
                is Resource.Loading -> {
                    ship.value = ShipStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    ship.value = ShipStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    ship.value = ShipStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}