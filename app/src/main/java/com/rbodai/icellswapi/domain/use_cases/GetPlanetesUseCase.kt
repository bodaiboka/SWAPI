package com.rbodai.icellswapi.domain.use_cases

import com.rbodai.icellswapi.data.api.Resource
import com.rbodai.icellswapi.domain.model.Planet
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(private val swapiRepo: SwapiRepo) {

    operator fun invoke(): Flow<Resource<Map<Int, Planet>>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(swapiRepo.getPlanets()))

        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}