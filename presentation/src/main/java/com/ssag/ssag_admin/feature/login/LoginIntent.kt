package com.ssag.ssag_admin.feature.login

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.ssag_admin.base.Intent

sealed class LoginIntent : Intent {
    data class SuccessLogin(val teacherEntity: TeacherEntity) : LoginIntent()
    data class InputPassword(val password: String) : LoginIntent()
    object StartLoading : LoginIntent()
    object FinishLoading : LoginIntent()
    object Logout : LoginIntent()
}