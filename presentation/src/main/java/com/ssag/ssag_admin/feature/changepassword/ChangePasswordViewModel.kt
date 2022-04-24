package com.ssag.ssag_admin.feature.changepassword

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
        if (state.value.newPassword != state.value.confirmPassword && state.value.confirmPassword.isNotBlank()) {
            sendIntent(ChangePasswordIntent.SetPasswordIsDifferent)
        } else {
            sendIntent(ChangePasswordIntent.SetPasswordIsSame)
        }
    }

    sealed class ChangePasswordEvent : Event {
        object ChangePasswordFail : ChangePasswordEvent()
    }
}