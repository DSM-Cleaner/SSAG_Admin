package com.ssag.domain.clean.entity

data class RoomStateEntity(
    val lightIsComplete: Boolean,
    val plugIsComplete: Boolean,
    val shoesAreComplete: Boolean,
    val students: List<StudentEntity>
) {

    data class StudentEntity(
        val id: Long,
        val bedPosition: String,
        val gcn: Int,
        val name: String,
        val cleanState: CleanStateEntity,
        val personalPlaceEntity: PersonalPlaceEntity
    )
}