package com.ssag.data.remote.datasource

import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.domain.clean.parameter.PostCleanStateParameter

interface CleanRemoteDataSource {

    suspend fun postCleanState(postCleanStateParameter: PostCleanStateParameter)

    suspend fun fetchRoomState(roomId: Int): RoomStateEntity
}