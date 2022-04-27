package com.ssag.data.repository

import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.domain.clean.parameter.PostCleanStateParameter
import com.ssag.domain.clean.repository.CleanRepository
import javax.inject.Inject

class CleanRepositoryImpl @Inject constructor(

) : CleanRepository{

    override fun postCleanState(postCleanStateParameter: PostCleanStateParameter) {
        TODO("Not yet implemented")
    }

    override fun fetchRoomState(roomId: Int): RoomStateEntity {
        TODO("Not yet implemented")
    }
}