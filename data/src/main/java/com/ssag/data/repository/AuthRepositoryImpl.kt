package com.ssag.data.repository

import com.ssag.data.datasource.remote.AuthRemoteDataSource
import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun login(password: String): TeacherEntity =
        authRemoteDataSource.login(password)

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword(changePasswordParameter: ChangePasswordParameter) {
        authRemoteDataSource.changePassword(changePasswordParameter)
    }
}