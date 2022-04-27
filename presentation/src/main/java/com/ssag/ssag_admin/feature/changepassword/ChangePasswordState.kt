package com.ssag.ssag_admin.feature.changepassword

import com.ssag.ssag_admin.base.State

data class ChangePasswordState(
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String,
    val isLoading: Boolean,
    val passwordIsDifferent: Boolean
) : State {

    companion object {
        fun initial() =
            ChangePasswordState(
                currentPassword = "",
                newPassword = "",
                confirmPassword = "",
                isLoading = false,
                passwordIsDifferent = false
            )
    }
}