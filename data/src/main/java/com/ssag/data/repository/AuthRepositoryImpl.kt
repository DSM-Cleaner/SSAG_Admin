package com.ssag.data.repository

import com.ssag.data.local.datasource.AuthLocalDataSource
import com.ssag.data.remote.datasource.AuthRemoteDataSource
import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.parameter.LoginParameter
import com.ssag.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun login(loginParameter: LoginParameter): TeacherEntity =
        authRemoteDataSource.login(loginParameter)

    override suspend fun logout() {
        authLocalDataSource.clearLocalData()
    }

    override suspend fun changePassword(changePasswordParameter: ChangePasswordParameter) {
        authRemoteDataSource.changePassword(changePasswordParameter)
    }
}