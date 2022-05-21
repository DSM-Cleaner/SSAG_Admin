package com.ssag.domain.feature.auth.parameter

data class ChangePasswordParameter(
    val currentPassword: String,
    val newPassword: String
)