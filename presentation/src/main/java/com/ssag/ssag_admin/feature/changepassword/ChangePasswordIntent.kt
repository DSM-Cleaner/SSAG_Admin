package com.ssag.ssag_admin.feature.changepassword

import com.ssag.ssag_admin.base.Intent

sealed class ChangePasswordIntent : Intent {

    data class InputCurrentPassword(val password: String) : ChangePasswordIntent()
    data class InputNewPassword(val password: String) : ChangePasswordIntent()
    data class InputConfirmPassword(val password: String) : ChangePasswordIntent()
    object SetPasswordIsDifferent : ChangePasswordIntent()
    object SetPasswordIsSame : ChangePasswordIntent()
    object StartLoading : ChangePasswordIntent()
    object FinishLoading : ChangePasswordIntent()
}