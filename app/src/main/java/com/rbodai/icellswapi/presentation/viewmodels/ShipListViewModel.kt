package com.rbodai.icellswapi.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbodai.icellswapi.data.api.Resource
import com.rbodai.icellswapi.domain.model.Ship
import com.rbodai.icellswapi.domain.use_cases.GetShipsUseCase
import com.rbodai.icellswapi.presentation.viewstates.ListViewStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShipListViewModel @Inject constructor(private val getShipsUseCase: GetShipsUseCase) :
    ViewModel() {

    val ships = mutableStateOf(ListViewStateHolder<List<Ship>>())

    init {
        getShips()
    }

    fun getShips() {
        getShipsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    ships.value = ListViewStateHolder(isLoading = true)
                }
                is Resource.Success -> {
                    ships.value = ListViewStateHolder(data = it.data)
                }
                is Resource.Error -> {
                    ships.value = ListViewStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}