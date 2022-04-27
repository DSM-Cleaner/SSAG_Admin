package com.ssag.domain.clean.repository

import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.domain.clean.parameter.PostCleanStateParameter

interface CleanRepository {

    suspend fun postCleanState(postCleanStateParameter: PostCleanStateParameter)

    suspend fun fetchRoomState(roomId: Int): RoomStateEntity
}