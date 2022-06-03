package com.ssag.ssag_admin.feature.login

sealed class LoginSideEffect {
    object FailedLogin : LoginSideEffect()
    object FailedAutoLogin : LoginSideEffect()
}