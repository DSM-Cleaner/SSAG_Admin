package com.ssag.ssag_admin.feature.clean

import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.ssag_admin.base.State

data class CheckCleanState(val roomNumber: Int, val cleanState: RoomStateEntity) : State {

    companion object {

        fun initial() =
            CheckCleanState(
                0,
                RoomStateEntity(
                    lightIsComplete = true,
                    plugIsComplete = true,
                    shoesAreComplete = true,
                    students = emptyList()
                )
            )
    }
}