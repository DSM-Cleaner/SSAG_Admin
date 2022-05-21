package com.ssag.data.repository

import com.ssag.data.local.datasource.AuthLocalDataSource
import com.ssag.data.remote.datasource.AuthRemoteDataSource
import com.ssag.data.remote.response.LoginResponse
import com.ssag.domain.exception.NeedLoginException
import com.ssag.domain.feature.auth.entity.TeacherEntity
import com.ssag.domain.feature.auth.parameter.ChangePasswordParameter
import com.ssag.domain.feature.auth.parameter.LoginParameter
import com.ssag.domain.feature.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun login(loginParameter: LoginParameter): TeacherEntity {
        authLocalDataSource.saveTeacher(loginParameter)
        return authRemoteDataSource.login(loginParameter).toEntity()
    }

    private fun LoginResponse.toEntity(): TeacherEntity {
        val name = authLocalDataSource.fetchTeacher().name
        return TeacherEntity(
            id = id,
            isManTeacher = gender,
            name = name
        )
    }

    override suspend fun logout() {
        authLocalDataSource.clearLocalData()
    }

    override suspend fun checkNeedLogin(): TeacherEntity {
        if (authLocalDataSource.isTokenEmpty()) {
            throw NeedLoginException()
        } else {
            val teacher = authLocalDataSource.fetchTeacher()
            return authRemoteDataSource.login(teacher).toEntity()
        }
    }

    override suspend fun changePassword(changePasswordParameter: ChangePasswordParameter) {
        authRemoteDataSource.changePassword(changePasswordParameter)
    }
}