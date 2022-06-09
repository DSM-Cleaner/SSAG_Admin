package com.ssag.domain.feature.clean.entity

data class RoomStateEntity(
    val lightIsNotComplete: Boolean,
    val plugIsNotComplete: Boolean,
    val shoesAreNotComplete: Boolean,
    val students: List<StudentEntity>
) {

    companion object {

        fun default(): RoomStateEntity =
            RoomStateEntity(
                lightIsNotComplete = false,
                plugIsNotComplete = false,
                shoesAreNotComplete = false,
                students = emptyList()
            )
    }
}