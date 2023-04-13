package com.rbodai.icellswapi.domain.use_cases

import com.rbodai.icellswapi.data.api.Resource
import com.rbodai.icellswapi.domain.model.Ship
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShipsUseCase @Inject constructor(private val swapiRepo: SwapiRepo) {

    operator fun invoke(): Flow<Resource<List<Ship>>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(swapiRepo.getShips()))

        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}