package com.ssag.domain.clean.entity

data class StudentEntity(
    val id: Long,
    val bedPosition: String,
    val gcn: Int,
    val name: String,
    val cleanState: CleanStateEntity
)