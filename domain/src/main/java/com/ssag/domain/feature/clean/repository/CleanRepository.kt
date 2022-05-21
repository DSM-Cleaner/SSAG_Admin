package com.ssag.domain.feature.clean.repository

import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.parameter.PostCleanStateParameter

interface CleanRepository {

    suspend fun postCleanState(postCleanStateParameter: PostCleanStateParameter)

    suspend fun fetchRoomState(roomId: Int): RoomStateEntity
}