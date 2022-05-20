package com.ssag.domain.auth.repository

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.parameter.LoginParameter

interface AuthRepository {

    suspend fun login(loginParameter: LoginParameter): TeacherEntity

    suspend fun logout()

    suspend fun changePassword(changePasswordParameter: ChangePasswordParameter)
}