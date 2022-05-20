package com.ssag.ssag_admin.feature.login

import com.ssag.ssag_admin.base.State

data class LoginState(
    val hasLogin: Boolean,
    val teacherName: String,
    val isManTeacher: Boolean,
    val isLoading: Boolean,
    val startFloor: Int,
    val name: String,
    val password: String
) : State {

    companion object {
        fun initial() =
            LoginState(
                hasLogin = false,
                teacherName = "",
                isManTeacher = true,
                isLoading = false,
                startFloor = 0,
                name = "",
                password = ""
            )
    }
}