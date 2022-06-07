package com.ssag.ssag_admin.feature.clean

import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.ssag_admin.base.State

data class CheckCleanState(
    val roomNumber: Int,
    val beforeRoomNumber: Int,
    val nextRoomNumber: Int,
    val roomIndex: Int,
    val roomList: List<Int>,
    val showSelectRoomDialog: Boolean,
    val roomState: RoomStateEntity,
    val isPersonalCheckDay: Boolean,
    val isManTeacher: Boolean
) : State {

    companion object {

        fun initial() =
            CheckCleanState(
                roomNumber = 0,
                beforeRoomNumber = 0,
                nextRoomNumber = 0,
                roomIndex = 0,
                roomList = emptyList(),
                showSelectRoomDialog = false,
                isPersonalCheckDay = false,
                isManTeacher = true,
                roomState = RoomStateEntity(
                    lightIsNotComplete = false,
                    plugIsNotComplete = false,
                    shoesAreNotComplete = false,
                    students = emptyList()
                )
            )
    }
}