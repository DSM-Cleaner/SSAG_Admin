package com.ssag.data.remote.datasource

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.parameter.LoginParameter

interface AuthRemoteDataSource {

    suspend fun login(loginParameter: LoginParameter): TeacherEntity

    suspend fun changePassword(changePasswordParameter: ChangePasswordParameter)
}