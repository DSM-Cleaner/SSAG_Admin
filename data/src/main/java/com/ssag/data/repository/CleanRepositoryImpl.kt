package com.ssag.data.repository

import com.ssag.data.remote.datasource.CleanRemoteDataSource
import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.parameter.PostCleanStateParameter
import com.ssag.domain.feature.clean.repository.CleanRepository
import javax.inject.Inject

class CleanRepositoryImpl @Inject constructor(
    private val cleanRemoteDataSource: CleanRemoteDataSource
) : CleanRepository{

    override suspend fun postCleanState(postCleanStateParameter: PostCleanStateParameter) {
        cleanRemoteDataSource.postCleanState(postCleanStateParameter)
    }

    override suspend fun fetchRoomState(roomId: Int): RoomStateEntity =
        cleanRemoteDataSource.fetchRoomState(roomId)
}