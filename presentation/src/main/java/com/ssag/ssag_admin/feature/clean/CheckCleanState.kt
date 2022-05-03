package com.ssag.ssag_admin.feature.clean

import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.ssag_admin.base.State

data class CheckCleanState(
    val roomNumber: Int,
    val showCollectRoomDialog: Boolean,
    val cleanState: RoomStateEntity
) : State {

    companion object {

        fun initial() =
            CheckCleanState(
                roomNumber = 0,
                showCollectRoomDialog = false,
                RoomStateEntity(
                    lightIsNotComplete = false,
                    plugIsNotComplete = false,
                    shoesAreNotComplete = false,
                    students = emptyList()
                )
            )
    }
}