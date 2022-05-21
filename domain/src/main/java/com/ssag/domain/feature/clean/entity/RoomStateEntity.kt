package com.ssag.domain.feature.clean.entity

data class RoomStateEntity(
    val lightIsNotComplete: Boolean,
    val plugIsNotComplete: Boolean,
    val shoesAreNotComplete: Boolean,
    val students: List<StudentEntity>
)