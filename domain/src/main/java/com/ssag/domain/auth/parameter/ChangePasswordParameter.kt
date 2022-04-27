package com.ssag.domain.auth.parameter

data class ChangePasswordParameter(
    val currentPassword: String,
    val newPassword: String
)