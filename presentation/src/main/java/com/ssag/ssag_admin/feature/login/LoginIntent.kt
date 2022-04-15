package com.ssag.ssag_admin.feature.login

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.ssag_admin.base.Intent

sealed class LoginIntent : Intent {
    data class SetTeacher(val teacherEntity: TeacherEntity) : LoginIntent()
    data class InputPassword(val password: String) : LoginIntent()
    object Logout : LoginIntent()
}