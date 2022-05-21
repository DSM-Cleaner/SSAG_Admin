package com.ssag.domain.feature.auth.repository

import com.ssag.domain.feature.auth.entity.TeacherEntity
import com.ssag.domain.feature.auth.parameter.ChangePasswordParameter
import com.ssag.domain.feature.auth.parameter.LoginParameter

interface AuthRepository {

    suspend fun login(loginParameter: LoginParameter): TeacherEntity

    suspend fun logout()

    suspend fun checkNeedLogin(): TeacherEntity

    suspend fun changePassword(changePasswordParameter: ChangePasswordParameter)
}