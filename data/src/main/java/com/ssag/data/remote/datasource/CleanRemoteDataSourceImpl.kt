package com.ssag.data.remote.datasource

import com.ssag.data.remote.api.CleanApi
import com.ssag.data.remote.request.toRequest
import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.domain.clean.parameter.PostCleanStateParameter
import javax.inject.Inject

class CleanRemoteDataSourceImpl @Inject constructor(
    private val cleanApi: CleanApi
) : CleanRemoteDataSource {

    override suspend fun postCleanState(postCleanStateParameter: PostCleanStateParameter) {
        val roomId = postCleanStateParameter.roomId
        val request = postCleanStateParameter.toRequest()
        cleanApi.postCleanState(roomId, request)
    }

    override suspend fun fetchRoomState(roomId: Int): RoomStateEntity =
        cleanApi.fetchRoomState(roomId).toEntity()
}