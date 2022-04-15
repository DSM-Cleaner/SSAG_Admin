package com.ssag.ssag_admin.feature.login

import com.ssag.ssag_admin.base.State

data class LoginState(
    val hasLogin: Boolean,
    val teacherName: String,
    val startFloor: Int,
    val password: String
) : State {

    companion object {
        fun initial() =
            LoginState(
                hasLogin = false,
                teacherName = "",
                startFloor = 0,
                password = ""
            )
    }
}