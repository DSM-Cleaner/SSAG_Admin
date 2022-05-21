package com.ssag.domain.feature.clean.usecase

import com.ssag.domain.base.UseCase
import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.repository.CleanRepository
import javax.inject.Inject

class FetchRoomStateUseCase @Inject constructor(
    private val cleanRepository: CleanRepository
) : UseCase<Int, RoomStateEntity>(){

    override suspend fun execute(data: Int): RoomStateEntity =
        cleanRepository.fetchRoomState(data)
}