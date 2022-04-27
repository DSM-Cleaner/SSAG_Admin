package com.ssag.domain.auth.repository

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter

interface AuthRepository {

    suspend fun login(password: String): TeacherEntity

    suspend fun logout()

    suspend fun changePassword(changePasswordParameter: ChangePasswordParameter)
}