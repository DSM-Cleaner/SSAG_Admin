package com.ssag.domain.auth.repository

import com.ssag.domain.auth.entity.TeacherEntity

interface AuthRepository {

    fun login(password: String): TeacherEntity

    fun logout()

    fun changePassword(newPassword: String)
}