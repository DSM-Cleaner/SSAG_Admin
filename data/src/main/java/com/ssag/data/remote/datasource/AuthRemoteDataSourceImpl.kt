package com.ssag.data.remote.datasource

import com.ssag.data.remote.api.AuthApi
import com.ssag.data.remote.request.ChangePasswordRequest
import com.ssag.data.remote.request.LoginRequest
import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRemoteDataSource {

    override suspend fun login(password: String): TeacherEntity =
        authApi.login(LoginRequest(password)).toEntity()

    override suspend fun changePassword(changePasswordParameter: ChangePasswordParameter) {
        val currentPassword = changePasswordParameter.currentPassword
        val newPassword = changePasswordParameter.newPassword
        authApi.changePassword(ChangePasswordRequest(currentPassword, newPassword))
    }
}