package com.ssag.ssag_admin.feature.clean

import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.ssag_admin.base.State

data class CheckCleanState(
    val roomNumber: Int,
    val showSelectRoomDialog: Boolean,
    val roomState: RoomStateEntity
) : State {

    companion object {

        fun initial() =
            CheckCleanState(
                roomNumber = 0,
                showSelectRoomDialog = false,
                RoomStateEntity(
                    lightIsNotComplete = false,
                    plugIsNotComplete = false,
                    shoesAreNotComplete = false,
                    students = emptyList()
                )
            )
    }
}