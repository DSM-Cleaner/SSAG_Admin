package com.ssag.ssag_admin.feature.changepassword

fun ChangePasswordState.reduceStartLoading() =
    this.copy(isLoading = true)

fun ChangePasswordState.reduceFinishLoading() =
    this.copy(isLoading = false)

fun ChangePasswordState.reduceInputCurrentPassword(password: String) =
    this.copy(currentPassword = password)

fun ChangePasswordState.reduceInputNewPassword(password: String) =
    this.copy(newPassword = password)

fun ChangePasswordState.reduceInputConfirmPassword(password: String) =
    this.copy(confirmPassword = password)

fun ChangePasswordState.reducePasswordIsDifferent() =
    this.copy(passwordIsDifferent = true)

fun ChangePasswordState.reducePasswordIsSame() =
    this.copy(passwordIsDifferent = false)

fun ChangePasswordState.isConfirmPasswordDifferent(): Boolean =
    newPassword != confirmPassword

fun ChangePasswordState.isConfirmPasswordSame(): Boolean =
    newPassword == confirmPassword

fun ChangePasswordState.isDoneInput(): Boolean =
    newPassword.isNotBlank() && currentPassword.isNotBlank() && confirmPassword.isNotBlank() && isConfirmPasswordSame()