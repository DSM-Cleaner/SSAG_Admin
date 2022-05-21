package com.ssag.data.remote.datasource

import com.ssag.data.remote.api.AuthApi
import com.ssag.data.remote.request.ChangePasswordRequest
import com.ssag.data.remote.request.toRequest
import com.ssag.domain.feature.auth.entity.TeacherEntity
import com.ssag.domain.feature.auth.parameter.ChangePasswordParameter
import com.ssag.domain.feature.auth.parameter.LoginParameter
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRemoteDataSource {

    override suspend fun login(loginParameter: LoginParameter): TeacherEntity =
        authApi.login(loginParameter.toRequest()).toEntity()

    override suspend fun changePassword(changePasswordParameter: ChangePasswordParameter) {
        val currentPassword = changePasswordParameter.currentPassword
        val newPassword = changePasswordParameter.newPassword
        authApi.changePassword(ChangePasswordRequest(currentPassword, newPassword))
    }
}