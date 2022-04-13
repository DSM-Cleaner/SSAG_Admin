package com.ssag.domain.clean.entity

data class RoomStateEntity(
    val lightIsComplete: Boolean,
    val plugIsComplete: Boolean,
    val shoesAreComplete: Boolean,
    val studentA: StudentEntity,
    val studentB: StudentEntity,
    val studentC: StudentEntity
) {

    data class StudentEntity(
        val gcn: Int,
        val name: String,
        val cleanState: CleanStateEntity
    )
}