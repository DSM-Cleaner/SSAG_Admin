package com.ssag.ssag_admin.feature.changepassword

sealed class ChangePasswordSideEffect {
    object ChangePasswordFail : ChangePasswordSideEffect()
    object ChangePasswordSuccess : ChangePasswordSideEffect()
    object NotDoneInput : ChangePasswordSideEffect()
}