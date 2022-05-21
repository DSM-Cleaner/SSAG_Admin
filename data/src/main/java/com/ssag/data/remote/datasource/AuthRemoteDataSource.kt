package com.ssag.data.remote.datasource

import com.ssag.data.remote.response.LoginResponse
import com.ssag.domain.feature.auth.entity.TeacherEntity
import com.ssag.domain.feature.auth.parameter.ChangePasswordParameter
import com.ssag.domain.feature.auth.parameter.LoginParameter

interface AuthRemoteDataSource {

    suspend fun login(loginParameter: LoginParameter): LoginResponse

    suspend fun changePassword(changePasswordParameter: ChangePasswordParameter)
}