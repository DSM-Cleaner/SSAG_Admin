package com.ssag.data.remote.datasource

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter

interface AuthRemoteDataSource {

    suspend fun login(password: String): TeacherEntity

    suspend fun changePassword(changePasswordParameter: ChangePasswordParameter)
}