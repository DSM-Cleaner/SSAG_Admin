package com.ssag.data.remote.response

import com.ssag.domain.auth.entity.TeacherEntity

data class LoginResponse(
    val id: Long,
    val name: String
) {

    fun toEntity() =
        TeacherEntity(
            id = id,
            name = name,
            isManTeacher = name == "박창수" || name == "조건웅"
        )
}