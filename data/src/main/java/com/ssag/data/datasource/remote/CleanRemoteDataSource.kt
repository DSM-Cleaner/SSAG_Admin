package com.ssag.data.datasource.remote

import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.domain.clean.parameter.PostCleanStateParameter

interface CleanRemoteDataSource {

    suspend fun postCleanState(postCleanStateParameter: PostCleanStateParameter)

    suspend fun fetchRoomState(): RoomStateEntity
}