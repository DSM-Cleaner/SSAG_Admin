package com.ssag.data.repository

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

) : AuthRepository {

    override suspend fun login(password: String): TeacherEntity {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword(newPassword: ChangePasswordParameter) {
        TODO("Not yet implemented")
    }
}