package com.ssag.ssag_admin.feature.changepassword

import androidx.lifecycle.ViewModel
import com.ssag.domain.feature.auth.parameter.ChangePasswordParameter
import com.ssag.domain.feature.auth.usecase.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase
) : ContainerHost<ChangePasswordState, ChangePasswordSideEffect>, ViewModel() {

    override val container: Container<ChangePasswordState, ChangePasswordSideEffect> =
        container(ChangePasswordState.initial())

    fun changePassword() = intent {
        if (state.isDoneInput()) {
            kotlin.runCatching {
                val newPassword = state.newPassword
                val currentPassword = state.currentPassword
                changePasswordUseCase.execute(
                    ChangePasswordParameter(
                        newPassword = newPassword,
                        currentPassword = currentPassword
                    )
                )
                reduce { state.reduceStartLoading() }
            }.onSuccess {
                postSideEffect(ChangePasswordSideEffect.ChangePasswordSuccess)
            }.onFailure {
                postSideEffect(ChangePasswordSideEffect.ChangePasswordFail)
            }.also {
                reduce { state.reduceFinishLoading() }
            }
        } else {
            postSideEffect(ChangePasswordSideEffect.NotDoneInput)
        }
    }

    fun inputCurrentPassword(password: String) = intent {
        reduce { state.reduceInputCurrentPassword(password) }
    }

    fun inputNewPassword(password: String) = intent {
        reduce { state.reduceInputNewPassword(password) }
        checkPasswordIsDifferent()
    }

    fun inputConfirmPassword(password: String) = intent {
        reduce { state.reduceInputConfirmPassword(password) }
        checkPasswordIsDifferent()
    }

    private fun checkPasswordIsDifferent() = intent {
        if (state.isConfirmPasswordDifferent() && state.confirmPassword.isNotBlank()) {
            reduce { state.reducePasswordIsDifferent() }
        } else {
            reduce { state.reducePasswordIsSame() }
        }
    }
}