package com.ssag.data.repository

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

) : AuthRepository {

    override fun login(password: String): TeacherEntity {
        TODO("Not yet implemented")
    }

    override fun changePassword(newPassword: String) {
        TODO("Not yet implemented")
    }
}