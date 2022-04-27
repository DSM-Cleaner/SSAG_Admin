package com.ssag.domain.clean.entity

data class RoomStateEntity(
    val lightIsNotComplete: Boolean,
    val plugIsNotComplete: Boolean,
    val shoesAreNotComplete: Boolean,
    val students: List<StudentEntity>
)