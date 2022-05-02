package com.ssag.ssag_admin.feature.changepassword

import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.usecase.ChangePasswordUseCase
import com.ssag.ssag_admin.base.BaseViewModel
import com.ssag.ssag_admin.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase
) : BaseViewModel<ChangePasswordState, ChangePasswordIntent, ChangePasswordViewModel.ChangePasswordEvent>() {

    override val initialState: ChangePasswordState
        get() = ChangePasswordState.initial()

    suspend fun changePassword() {
        if (state.value.isDoneInput()) {
            kotlin.runCatching {
                val newPassword = state.value.newPassword
                val currentPassword = state.value.currentPassword
                changePasswordUseCase.execute(
                    ChangePasswordParameter(
                        newPassword = newPassword,
                        currentPassword = currentPassword
                    )
                )
                sendIntent(ChangePasswordIntent.StartLoading)
            }.onSuccess {
                sendEvent(ChangePasswordEvent.ChangePasswordSuccess)
            }.onFailure {
                sendEvent(ChangePasswordEvent.ChangePasswordFail)
            }.also {
                sendIntent(ChangePasswordIntent.FinishLoading)
            }
        } else {
            sendEvent(ChangePasswordEvent.NotDoneInput)
        }
    }

    fun inputCurrentPassword(password: String) {
        sendIntent(ChangePasswordIntent.InputCurrentPassword(password))
    }

    fun inputNewPassword(password: String) {
        sendIntent(ChangePasswordIntent.InputNewPassword(password))
    }

    fun inputConfirmPassword(password: String) {
        sendIntent(ChangePasswordIntent.InputConfirmPassword(password))
    }

    override fun reduceIntent(oldState: ChangePasswordState, intent: ChangePasswordIntent) {
        when (intent) {

            is ChangePasswordIntent.InputCurrentPassword -> {
                setState(
                    oldState.copy(
                        currentPassword = intent.password
                    )
                )
            }

            is ChangePasswordIntent.InputNewPassword -> {
                setState(
                    oldState.copy(
                        newPassword = intent.password
                    )
                )

                checkPasswordIsDifferent()
            }

            is ChangePasswordIntent.InputConfirmPassword -> {
                setState(
                    oldState.copy(
                        confirmPassword = intent.password
                    )
                )
            }

            is ChangePasswordIntent.StartLoading -> {
                setState(
                    oldState.copy(
                        isLoading = true
                    )
                )
            }

            is ChangePasswordIntent.FinishLoading -> {
                setState(
                    oldState.copy(
                        isLoading = false
                    )
                )
            }

            is ChangePasswordIntent.SetPasswordIsDifferent -> {
                setState(
                    oldState.copy(
                        passwordIsDifferent = true
                    )
                )
            }

            is ChangePasswordIntent.SetPasswordIsSame -> {
                setState(
                    oldState.copy(
                        passwordIsDifferent = false
                    )
                )
            }
        }
    }

    private fun checkPasswordIsDifferent() {
        if (state.value.isConfirmPasswordDifferent() && state.value.confirmPassword.isNotBlank()) {
            sendIntent(ChangePasswordIntent.SetPasswordIsDifferent)
        } else {
            sendIntent(ChangePasswordIntent.SetPasswordIsSame)
        }
    }

    private fun ChangePasswordState.isConfirmPasswordDifferent(): Boolean =
        newPassword != confirmPassword

    private fun ChangePasswordState.isConfirmPasswordSame(): Boolean =
        newPassword == confirmPassword

    private fun ChangePasswordState.isDoneInput(): Boolean =
        newPassword.isNotBlank() && currentPassword.isNotBlank() && confirmPassword.isNotBlank() && isConfirmPasswordSame()

    sealed class ChangePasswordEvent : Event {
        object ChangePasswordFail : ChangePasswordEvent()
        object ChangePasswordSuccess : ChangePasswordEvent()
        object NotDoneInput : ChangePasswordEvent()
    }
}