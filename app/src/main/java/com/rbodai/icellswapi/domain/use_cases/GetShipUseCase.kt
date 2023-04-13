package com.rbodai.icellswapi.domain.use_cases

import com.rbodai.icellswapi.data.api.Resource
import com.rbodai.icellswapi.domain.model.Ship
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShipUseCase @Inject constructor(private val swapiRepo: SwapiRepo) {

    operator fun invoke(id: Int): Flow<Resource<Ship>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(swapiRepo.getShip(id)))

        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}