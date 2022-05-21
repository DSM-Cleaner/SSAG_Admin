package com.ssag.domain.feature.clean.entity

data class StudentEntity(
    val id: Long,
    val bedPosition: String,
    val gcn: Int,
    val name: String,
    val cleanState: CleanStateEntity
)